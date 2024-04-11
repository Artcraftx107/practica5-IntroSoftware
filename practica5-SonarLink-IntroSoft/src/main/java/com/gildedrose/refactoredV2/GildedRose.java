package com.gildedrose.refactoredV2;

class GildedRose {
  public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
  public static final String AGED_BRIE = "Aged Brie";
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  public static final int MAX_QUALITY = 50;
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      updateItem(item);
    }
  }

  private void updateItem(Item item) {
    switch (item.name) {
      case SULFURAS:
        //Do absolutely nothing
        break;
      case AGED_BRIE:
        increaseQuality(item);
        updateSellIn(item);
        if (hasExpired(item)) {
          increaseQuality(item);
        }
        break;
      case BACKSTAGE_PASS:
        increaseQuality(item);
        if (item.sellIn < 11) {
          increaseQuality(item);
        }
        if (item.sellIn < 6) {
          increaseQuality(item);
        }
        updateSellIn(item);
        if(hasExpired(item)){
          item.quality=0;
        }
        break;
      default:
        decreaseQuality(item);
        updateSellIn(item);
        if(hasExpired(item)){
          decreaseQuality(item);
        }
        break;
    }
  }

  private void updateSellIn(Item item) {
    item.sellIn = item.sellIn - 1;
  }

  private boolean hasExpired(Item item) {
    return item.sellIn < 0;
  }

  private void increaseQuality(Item item) {
    if (item.quality < MAX_QUALITY) {
      item.quality = item.quality + 1;
    }
  }

  private void decreaseQuality(Item item) {
    if (item.quality > 0) {
      item.quality = item.quality - 1;
    }
  }
}
