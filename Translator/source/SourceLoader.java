package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SourceLoader should contains all implementations of SourceProviders to be able to load different sources.
 */
public class SourceLoader {
    private List<SourceProvider> sourceProviders = new ArrayList<>();

    public SourceLoader() {
    	sourceProviders.add(new FileSourceProvider());
    	sourceProviders.add(new URLSourceProvider());
    }

    public String loadSource(String pathToSource) throws IOException {

    	String text = "Wrong path";
    	for(SourceProvider sp: sourceProviders)
            if(sp.isAllowed(pathToSource)) text = sp.load(pathToSource);
        return text;
    }
}
