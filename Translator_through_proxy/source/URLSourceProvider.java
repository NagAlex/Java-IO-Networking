package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;*/
import java.net.*;


/**
 * Implementation for loading content from specified URL.<br/>
 * Valid paths to load are http://someurl.com, https://secureurl.com, ftp://frpurl.com etc.
 */
public class URLSourceProvider implements SourceProvider {

    public boolean isAllowed(String pathToSource) {
        try {
            InetSocketAddress iaddr = new InetSocketAddress(InetAddress.getByName("proxy.fcbank.com.ua"), 3128);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, iaddr);

            URLConnection urlc = (new URL(pathToSource)).openConnection(proxy);
            System.out.println("Connected through proxy...");
            if (urlc != null) return true;
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public String load(String pathToSource) throws IOException {
    	System.out.println("\nConnecting...");

        InetSocketAddress iaddr = new InetSocketAddress(InetAddress.getByName("proxy.fcbank.com.ua"), 3128);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, iaddr);

    	try(BufferedReader reader = new BufferedReader(
    				    new InputStreamReader(
    					  (new URL(pathToSource)).openConnection(proxy).getInputStream(), "UTF-8"
    				    )
    				)
    	){
    	    System.out.println("Connection OK.\n");
	    int next;
	    StringBuffer transl = new StringBuffer();

	    while((next = reader.read()) != -1) {
	    	transl = transl.append((char) next);
	    }

	    return transl.toString();

    	} catch (IOException e) {
    	    System.out.println("Cannot read from URL: " + e);
    	}
        return null;
    }
}
