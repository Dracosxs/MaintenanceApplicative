package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private GildedRose app;

    @BeforeEach
    void setup() {
        Item[] items = {
                new Item("Aged Brie", 10, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 30),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Conjured Mana Cake", 5, 10),
                new Item("Normal Item", 3, 6)
        };
        app = new GildedRose(items);
    }

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.getItems()[0].name);
    }

    @Test
    void testNormalItem() {
        app.updateQuality();
        assertEquals(5, app.getItems()[4].quality, "La qualité d'un item normal diminue de 1");
        assertEquals(2, app.getItems()[4].sellIn, "sellIn diminue de 1");
    }


    @Test
    void testAgedBrieIncreasesQuality() {
        app.updateQuality();
        assertEquals(21, app.getItems()[0].quality, "Aged Brie augmente en qualité");
    }

    @Test
    void testBackstagePassesIncreaseQuality() {
        app.updateQuality();
        assertEquals(31, app.getItems()[1].quality, "Backstage passes augmentent en qualité");
    }

    @Test
    void testBackstagePassesDropToZero() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app2 = new GildedRose(items);
        app2.updateQuality();
        assertEquals(0, app2.getItems()[0].quality, "Backstage passes chutent à 0 après l'événement");
    }

    @Test
    void testSulfurasUnchanged() {
        app.updateQuality();
        assertEquals(80, app.getItems()[2].quality, "Sulfuras ne change jamais");
        assertEquals(0, app.getItems()[2].sellIn, "Sulfuras ne change jamais de sellIn");
    }

    @Test
    void testConjuredItemsDegradeTwiceAsFast() {
        app.updateQuality();
        assertEquals(8, app.getItems()[3].quality, "Les items Conjured se dégradent 2x plus vite");
    }

    @Test
    void testQualityNeverNegative() {
        Item[] items = { new Item("Normal Item", 1, 0) };
        GildedRose app2 = new GildedRose(items);
        app2.updateQuality();
        assertEquals(0, app2.getItems()[0].quality, "La qualité ne peut jamais être négative");
    }

    @Test
    void testQualityNeverExceedsFifty() {
        Item[] items = { new Item("Aged Brie", 5, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.getItems()[0].quality, "La qualité ne doit jamais dépasser 50");
    }

    @Test
    void testNormalItemAfterSellInDate() {
        Item[] items = { new Item("Normal Item", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.getItems()[0].quality, "La qualité doit baisser de 2 après expiration");
    }


    @Test
    void testItemOverSeveralDays() {
        Item[] items = { new Item("Normal Item", 2, 6) };
        GildedRose app = new GildedRose(items);

        app.updateQuality(); // Jour 1
        assertEquals(5, app.getItems()[0].quality);
        assertEquals(1, app.getItems()[0].sellIn);

        app.updateQuality(); // Jour 2
        assertEquals(4, app.getItems()[0].quality);
        assertEquals(0, app.getItems()[0].sellIn);

        app.updateQuality(); // Jour 3 (périmé)
        assertEquals(2, app.getItems()[0].quality);
        assertEquals(-1, app.getItems()[0].sellIn);
    }

    @Test
    void testConjuredItemAfterSellInDate() {
        Item[] items = { new Item("Conjured Mana Cake", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.getItems()[0].quality, "La qualité doit baisser de 4 après expiration");
    }

    @Test
    void testAgedBrieAfterSellInDate() {
        Item[] items = { new Item("Aged Brie", -1, 40) }; // Périmé mais qualité < 50
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(42, app.getItems()[0].quality, "Aged Brie doit augmenter de 2 après expiration");
    }
}
