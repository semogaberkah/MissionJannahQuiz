package com.feedit.missionjannahquiz;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

public class Loading extends AsyncTask<String, Integer, Integer> {
	public static int progressStatus=100;
	public static boolean isStart = false;
	public interface LoadingTaskFinishedListener {
		void onTaskFinished();
	}

	private final ProgressBar progressBar;
	private final LoadingTaskFinishedListener finishedListener;

	public Loading(ProgressBar progressBar,
                   LoadingTaskFinishedListener finishedListener) {
		this.progressBar = progressBar;
		this.finishedListener = finishedListener;
	}

	@Override
	protected Integer doInBackground(String... params) {
		Log.i("Tutorial", "Starting task with url: " + params[0]);
		if (resourcesDontAlreadyExist()) {
			downloadResources();
		}
		return 1234;

	}

	private boolean resourcesDontAlreadyExist() {
		return true;
	}

	private void downloadResources() {

		while (progressStatus>=0) {
			publishProgress(progressStatus);

			try {
				Thread.sleep(200);
			} catch (InterruptedException ignore) {
			}

			if(isStart==true){
				progressStatus--;
			}

		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(progressStatus);
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		finishedListener.onTaskFinished();
	}

}
