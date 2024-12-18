package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

import model.bo.VideoBO;

public class SaveVideoProcessing extends Thread {
	// <<username, id>, progress>
	public static Map<Pair<String,String>,AtomicReference<Double>> progressMap = new HashMap<>();

	public static Queue<SaveVideoEntity> queue = new ConcurrentLinkedQueue<SaveVideoEntity>();

	public static synchronized SaveVideoEntity getSaveVideoEntity() {
		if (queue.size() > 0)
			return queue.poll();
		else
			return null;
	}

	@Override
	public void run() {
		while (true) {
			SaveVideoEntity sve = getSaveVideoEntity();
			if (sve != null) {
				File uploadDir = new File(sve.uploadPath);
				if (!uploadDir.exists())
					uploadDir.mkdir();

				
				String fileName = sve.id;

				VideoBO.them(new model.bean.Video(sve.id, sve.username, sve.name));
				
				try (FileOutputStream outputStream = new FileOutputStream(sve.uploadPath + File.separator + fileName)) {
					// Cập nhật giá trị
					AtomicReference<Double> value = new AtomicReference<>(0.0);
					Pair<String, String> key = new Pair<>(sve.username, sve.id);
					progressMap.put(key, value);
					
					
					byte[] buffer = new byte[8192];
					int bytesRead;
					long total = 0;
					
					while ((bytesRead = sve.inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
						total += bytesRead;
					    value.set((double) total / sve.size * 100); // Cập nhật giá trị trong Map
					    Thread.sleep(0, 1000);
					}
					
					progressMap.remove(key);
					
					sve.inputStream.close();
					
					System.out.println("File saved successfully: " + fileName);
				} catch (IOException e) {
					System.err.println("Error saving file: " + e.getMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(100);
				} catch (Exception ex) {

				}
			}
		}
	}
}
