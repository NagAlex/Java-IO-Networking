import source.SourceLoader;
import source.URLSourceProvider;

import java.io.IOException;
import java.util.Scanner;

public class TranslatorController {

    public static void main(String[] args) throws IOException {
        //initialization
        SourceLoader sourceLoader = new SourceLoader();
        Translator translator = new Translator(new URLSourceProvider());

        Scanner scanner = new Scanner(System.in);
        String command = null;
        String source = null;
        String translation = null;
        do {
	    System.out.print("Enter the Path with text to translate (type 'exit' to exit): ");
            command = scanner.next();
            //exception handling to let user know about it and ask him to enter another path to translation
            //So, the only way to stop the application is to do that manually or type "exit"
            if(!"exit".equals(command)){
            	try {
            	    source = sourceLoader.loadSource(command);
            	    if(source.equals("Wrong path")) {
            	    	System.out.println("\nThe path you have entered is wrong. Please enter another path.\n");
            	    	continue;
            	    }
            	} catch (IOException e) {
            	    System.out.println(e.getMessage());
            	}
            	
                if(source != null ) {
            	    translation = translator.translate(source);
            	    System.out.println("Original: \n" + source + "\n");
            	    System.out.println("Translation: \n" + translation + "\n");
            	}
            	source = translation = null;
            }
        } while(!"exit".equals(command));
    }
}
