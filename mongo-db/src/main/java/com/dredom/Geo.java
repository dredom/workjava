package com.dredom;

/**
 * Geographic location in decimal degrees.
 * This is used when searching for stores in any area.
 *
 * @author Andre Untiedt
 *
 */
public class Geo {

	private double latitude;
	private double longitude;

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
