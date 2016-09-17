package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	private InputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {		
		return in.read();
	}
	
	@Override
	public int read(byte[] arr) throws IOException {
		int k = 0;
		byte b = 0;
		byte count = 0;
		//boolean eof =false;
		while (k < arr.length && count != -1 && b !=-1 ) {
			count = (byte) in.read();
			b = (byte) in.read();
			if (b==8)
			{
				System.out.println(count);
			}
			if (count != -1 && b !=-1)
			{
				for (int j = 0; j < count; j++) {
					arr[k++] = b;
				}
			}
			
		}
		
		return arr.length;		
	}
}
