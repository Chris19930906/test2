package com.bn.sample10_6;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sample10_6Activity extends Activity {
    SensorManager mySm;
    Sensor myS;
    TextView tv;
    TextView tvY;
    TextView tvP;
    TextView tvR;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mySm=(SensorManager)this.getSystemService(SENSOR_SERVICE);
        myS=mySm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        StringBuffer str=new StringBuffer();
        str.append("\n名称：");
        str.append(myS.getName());
        str.append("\n耗电量（mA）：");
        str.append(myS.getPower());
        str.append("\n类型编号：");
        str.append(myS.getType());
        str.append("\n版本：");
        str.append(myS.getVersion());
        str.append("\n最大测量范围：");
        str.append(myS.getMaximumRange());
        
        tv=(TextView)this.findViewById(R.id.textView4);
        tv.setText(str);
        tvY=(TextView)this.findViewById(R.id.textView1);
        tvP=(TextView)this.findViewById(R.id.textView2);
        tvR=(TextView)this.findViewById(R.id.textView3);
    }
    
    private SensorEventListener mySel=new SensorEventListener(){
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}

		@Override
		public void onSensorChanged(SensorEvent event) {
			float []value=event.values;
			tvY.setText("Yaw值为："+value[0]);
			tvP.setText("Pitch值为："+value[1]);
			tvR.setText("Roll值为："+value[2]);
		}
    };
    
	@Override
	protected void onResume() {
		super.onResume();
		mySm.registerListener(mySel, myS, SensorManager.SENSOR_DELAY_NORMAL);
	}
    
	@Override
	protected void onPause() {
		super.onPause();
		mySm.unregisterListener(mySel);
	}
}