package app.akexorcist.sensor_humidity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class Main extends Activity {
	
	TextView textHumidity;
	SensorManager sensorManager;
	Sensor sensor;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
		
		textHumidity = (TextView) findViewById(R.id.textHumidity);
	}
 
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(humidListener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
 
	public void onStop() {
		super.onStop();
		sensorManager.unregisterListener(humidListener);
	}

	public SensorEventListener humidListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int acc) { }
 
		public void onSensorChanged(SensorEvent event) {
			float pressure = event.values[0];
			textHumidity.setText("Humidity is: " + pressure + " %");
		}
	};
}