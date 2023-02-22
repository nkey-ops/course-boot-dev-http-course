package practicehttp.course.async4.async1;

public class Application {

	public static void main(String[] args) {
		int smeltingIronBarsWait = 5;
		int combiningMaterialsWait = 10;
		int shapingIronWait = 30;
		int craftingCompleteWait = 40;

		setTimeout(() -> System.out.println("Iron Longsword Complete!"), craftingCompleteWait);
		setTimeout(() -> System.out.println("Combining Materials..."), combiningMaterialsWait);
		setTimeout(() -> System.out.println("Smelting Iron Bars..."), smeltingIronBarsWait);
		setTimeout(() -> System.out.println("Shaping Iron..."), shapingIronWait);

		System.out.println("Firing up the forge...");
	}

	private static void setTimeout(Runnable runnable, int sleepFor) {
		new Thread(() -> {
			try {
				Thread.sleep(sleepFor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			runnable.run();
		}).start();
		
		
		
	}

}
