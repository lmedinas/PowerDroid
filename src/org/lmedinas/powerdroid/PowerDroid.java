package org.lmedinas.powerdroid;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class PowerDroid extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/** TabHost will have Tabs */
		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

		/** TabSpec used to create a new tab.
		 * By using TabSpec only we can able to setContent to the tab.
		 * By using TabSpec setIndicator() we can set name to tab. */

		/** tid1 is firstTabSpec Id. Its used to access outside. */
		TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
		TabSpec secondTabSpec = tabHost.newTabSpec("tid1");

		/** TabSpec setIndicator() is used to set name for the tab. */
		/** TabSpec setContent() is used to set content for a particular tab. */
		firstTabSpec.setIndicator("PowerDroid Info").setContent(new Intent(this,PDInfo.class));
		secondTabSpec.setIndicator("Misc").setContent(new Intent(this,PDInfo2.class));

		/** Add tabSpec to the TabHost to display. */
		tabHost.addTab(firstTabSpec);
		tabHost.addTab(secondTabSpec);
	}
}