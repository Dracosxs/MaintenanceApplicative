package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    private Item[] items;
    private Map<String, ItemUpdater> strategies;

    public GildedRose(Item[] items) {
        this.items = items;
        this.strategies = new HashMap<>();

        strategies.put("Aged Brie", new AgedBrieUpdater());
        strategies.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater());
        strategies.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdater());
        strategies.put("Conjured", new ConjuredUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = strategies.getOrDefault(getItemCategory(item.name), new NormalItemUpdater());
            updater.update(item);
        }
    }

    private String getItemCategory(String name) {
        if (name.contains("Conjured")) {
            return "Conjured";
        }
        return name;
    }

    // Stratégies intégrées dans le même fichier
    interface ItemUpdater {
        void update(Item item);
    }

    class NormalItemUpdater implements ItemUpdater {
        public void update(Item item) {
            decreaseQuality(item);
            item.sellIn--;
            if (item.sellIn < 0) {
                decreaseQuality(item);
            }
        }

        private void decreaseQuality(Item item) {
            if (item.quality > 0) {
                item.quality--;
            }
        }
    }

    class AgedBrieUpdater implements ItemUpdater {
        public void update(Item item) {
            increaseQuality(item);
            item.sellIn--;
            if (item.sellIn < 0) {
                increaseQuality(item);
            }
        }

        private void increaseQuality(Item item) {
            if (item.quality < 50) {
                item.quality++;
            }
        }
    }

    class BackstagePassUpdater implements ItemUpdater {
        public void update(Item item) {
            if (item.sellIn > 10) {
                increaseQuality(item, 1);
            } else if (item.sellIn > 5) {
                increaseQuality(item, 2);
            } else if (item.sellIn > 0) {
                increaseQuality(item, 3);
            } else {
                item.quality = 0;
            }
            item.sellIn--;
        }

        private void increaseQuality(Item item, int amount) {
            item.quality = Math.min(item.quality + amount, 50);
        }
    }

    class SulfurasUpdater implements ItemUpdater {
        public void update(Item item) {
            // Sulfuras ne change jamais
        }
    }

    class ConjuredUpdater implements ItemUpdater {
        public void update(Item item) {
            decreaseQuality(item, 2);
            item.sellIn--;
            if (item.sellIn < 0) {
                decreaseQuality(item, 2);
            }
        }

        private void decreaseQuality(Item item, int amount) {
            item.quality = Math.max(item.quality - amount, 0);
        }
    }

    public Item[] getItems() {
        return items;
    }

}