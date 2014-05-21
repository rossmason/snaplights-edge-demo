package org.mule.examples.snaplights;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.mule.util.IOUtils;


@JsonAutoDetect
public class ImageRef {

	@JsonProperty("image")
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public byte[] getImageBytes() throws IOException {
		URL img = new URL(getUrl());
		InputStream in = null;
		try {
			in = img.openStream();
			byte[] b = IOUtils.toByteArray(in);
			return b;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	
}
