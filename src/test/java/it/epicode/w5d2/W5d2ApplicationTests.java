package it.epicode.w5d2;

import it.epicode.GestionePrenotazioni.W5d2Application;
import it.epicode.w5d2.bean.Topping;
import it.epicode.w5d2.components.Ordine;
import it.epicode.w5d2.components.Tavolo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class W5d2ApplicationTests {

    static AnnotationConfigApplicationContext context;

    @BeforeAll
    static void openContext() {
        context = new AnnotationConfigApplicationContext(W5d2Application.class);
    }

    @AfterAll
    static void closeContext() {
        context.close();
    }

    @Test
    void verificCostoCoperto() {
        double c = context.getBean("cc", Double.class);
        Assertions.assertEquals(1.5, c);
    }

    @Test
    void verificaSeStatoTavoloNullEStatoOrdineNull() {
        Ordine o = context.getBean(Ordine.class);
        Tavolo t = context.getBean(Tavolo.class);
        Assertions.assertAll(
                () -> Assertions.assertNull(o.getStato()),
                () -> Assertions.assertNull(t.getStato())
        );
    }

    @Test
    void verificaDipendenzaDiOrdineDaTavolo() {
        Ordine o = context.getBean(Ordine.class);
        Tavolo t = context.getBean(Tavolo.class);
        Assertions.assertSame(t, o.getTavolo());
    }

    @ParameterizedTest
    @CsvSource({"cheese, 300, 1", "TOMATO, 150, 0.5", "onions, 50, 1.5", "ham, 350, 2.5", "salami, 400, 3"})
    void verificaCalorieEPrezzoDiTuttiIToppings(String nome, int calorieAttese, double prezzoAtteso) {
        List<Topping> lista = context.getBean("lista_toppings", List.class);
        Topping t = lista.stream().filter(to -> to.getNome().equalsIgnoreCase(nome)).findAny().get();
        Assertions.assertAll(
                () -> Assertions.assertEquals(calorieAttese, t.getCalorie()),
                () -> Assertions.assertEquals(prezzoAtteso, t.getPrezzo())
        );
    }

    @ParameterizedTest
    @CsvSource({"0.5, 3, 1.7"})
    void verificaPrezzoMinimoEMassimoEMedioDeiToppings(double prezzoMinAtteso, double prezzoMaxAtteso, double prezzoAvAtteso) {
        List<Topping> lista = context.getBean("lista_toppings", List.class);
        double calcMin = lista.stream().mapToDouble(Topping::getPrezzo).min().getAsDouble();
        double calcMax = lista.stream().mapToDouble(Topping::getPrezzo).max().getAsDouble();
        double calcAv = lista.stream().mapToDouble(Topping::getPrezzo).average().getAsDouble();
        Assertions.assertAll(
                () -> Assertions.assertEquals(prezzoMinAtteso, calcMin),
                () -> Assertions.assertEquals(prezzoMaxAtteso, calcMax),
                () -> Assertions.assertEquals(prezzoAvAtteso, calcAv)
        );

    }

    static Stream<String> fornisciINomiPerIToppings() {
        List<Topping> lista = context.getBean("lista_toppings", List.class);
        return lista.stream().map(Topping::getNome);
    }

    @ParameterizedTest
    @MethodSource("fornisciINomiPerIToppings")
    void verificaINomiDeiToppings(String nome) {
        List<Topping> lista = context.getBean("lista_toppings", List.class);
        Assertions.assertTrue(lista.stream().map(Topping::getNome).toList().contains(nome));
    }
}
