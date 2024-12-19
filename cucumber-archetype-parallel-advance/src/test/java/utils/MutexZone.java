package utils;

import utils.constants.Constant;

import java.util.concurrent.Semaphore;

public class MutexZone {
	private static final Semaphore semaphore = new Semaphore(Constant.MAX_ITERATIONS);

	private MutexZone() {
	}

	public static void accessResource(Runnable action) throws InterruptedException {
		try {
			// Acquire a permit
			semaphore.acquire();

			// Get the start time
			long startTime = System.currentTimeMillis();

			// Execute the action
			action.run();

			// Get the time elapsed since the start time
			long elapsedTime = System.currentTimeMillis() - startTime;

			// If the elapsed time is less than 1 second, sleep for the remaining time
			int ELAPSED_TIME = 3000;
			if (elapsedTime < ELAPSED_TIME) {
				int MINIMUM_TIME_PERMITTED = 1000;
				Thread.sleep(MINIMUM_TIME_PERMITTED - elapsedTime);
			}

		} finally {
			// Release the permit
			semaphore.release();
		}
	}
}
