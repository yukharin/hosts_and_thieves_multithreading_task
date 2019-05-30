package com.yukharin.home_types;


//
//public class ItemsHome {
//
//    private Object lock;
//    private static final long TIMEOUT_SECONDS = 1L;
//    private List<Item> items;
//
//
//    public ItemsHome() {
//        this.items = new ArrayList<>();
//        lock = new Object();
//    }
//
//    @Override
//    public void put(Item element) {
//        synchronized (lock) {
//            items.add(element);
//        }
//    }
//
//
//    @Override
//    public Item get(int index) {
//        synchronized (lock) {
//            while (items.isEmpty()) {
//                try {
//                    lock.wait(TimeUnit.SECONDS.toSeconds(TIMEOUT_SECONDS));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            Item item = items.get(index);
//            items.remove(index);
//            lock.notifyAll();
//            return item;
//        }
//    }
//
//
//    @Override
//    public Item getRandomElement() {
//        synchronized (lock) {
//            while (items.isEmpty()) {
//                try {
//                    lock.wait(TimeUnit.SECONDS.toSeconds(TIMEOUT_SECONDS));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            Random random = new Random();
//            int randomIndex = random.nextInt(items.size());
//            Item item = items.get(randomIndex);
//            items.remove(randomIndex);
//            lock.notifyAll();
//            return item;
//        }
//    }
//
//
//    @Override
//    public void sortElements() {
//        synchronized (lock) {
//            while (items.isEmpty()) {
//                try {
//                    lock.wait(TimeUnit.SECONDS.toSeconds(TIMEOUT_SECONDS));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            Comparator comparator = new ItemComparator();
//            Collections.sort(items, comparator);
//        }
//    }
//
//    @Override
//    public List<Item> getAllElements() {
//        synchronized (lock) {
//            while (items.isEmpty()) {
//                try {
//                    lock.wait(TimeUnit.SECONDS.toSeconds(TIMEOUT_SECONDS));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return items;
//    }
//
//
//    public synchronized String toString() {
//        return items.toString();
//    }


