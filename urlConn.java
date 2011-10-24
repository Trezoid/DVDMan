public class urlConn {
	private byte[] content = null;
	private int responseCode = -1;
	private String MIMEtype = null;
	private String charset = null;

	public urlConn(String URLString)
	{
		java.net.URL url = null;
		java.net.URLConnection uconn = null;
		try{
			url = new java.net.URL(URLString);
			uconn = url.openConnection();
		}catch(Exception e){};
		if( !(uconn instanceof java.net.HttpURLConnection) )
		{
			throw new java.lang.IllegalArgumentException("URL protocol must be HTTP.");
		}
		final java.net.HttpURLConnection conn = (java.net.HttpURLConnection)uconn;

		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		conn.setInstanceFollowRedirects(true);
		conn.setRequestProperty("User-agent", "spider");
		try{
			conn.connect();
			responseCode = conn.getResponseCode();
		}catch(Exception e){}

		final int length = conn.getContentLength();
		final String type = conn.getContentType();

		if(type != null){
			final String[] parts = type.split(";");
			MIMEtype = parts[0].trim();
			for(int i = 1; i < parts.length && charset == null; i++)
			{
				final String t = parts[i].trim();
				final int index = t.toLowerCase().indexOf("charset=");
				if (index != -1)
				{
					charset = t.substring(index + 8);
				}
			}
		}
		final java.io.InputStream stream = conn.getErrorStream();
		java.io.InputStream tempStream = null; 
		try{
			if(stream != null)
			{
				content = (byte[])readStream(length, stream);
			}
			else if((tempStream = (java.io.InputStream)conn.getContent()) != null && tempStream instanceof java.io.InputStream)
			{
				content = (byte[])readStream(length, tempStream);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		conn.disconnect();
	}

	private byte[] readStream(int length, java.io.InputStream stream) throws java.io.IOException
	{
		final int buflen = Math.max(1024, Math.max(length, stream.available()));
		byte[] buf = new byte[buflen];
		byte[] bytes = null;

		for(int nRead = stream.read(buf); nRead != -1; nRead = stream.read(buf))
		{
			if(bytes == null)
			{
				bytes = buf;
				buf = new byte[buflen];
				continue;
			}
			final byte[] newBytes = new byte[bytes.length + nRead];
			System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
			System.arraycopy(buf, 0, newBytes, bytes.length, nRead);
			bytes = newBytes;
		}
		return bytes;
	}

	private byte[] getContentStream()
	{
		return content;
	}

	public String getContent(){
		try{
			return new String(content);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return "0";
		}
	}

	public int getResponseCode(){
		return responseCode;
	}

}	

