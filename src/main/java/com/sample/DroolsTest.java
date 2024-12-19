package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static JFrame frame;	
	
    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
        	
            Generator gen = new Generator();
            kSession.insert(gen);
            Scanner scanner = new Scanner(System.in);
            kSession.setGlobal("scanner", scanner);
            
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static class Generator {
    	
    
	    public static String askQuestion( String tresc, String y, String n ) {
	    	Object[] options = { y , n};
	    	int x = JOptionPane.showOptionDialog(frame,
	                tresc,
	                "Question",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                options,
	                options[0]);
	    	return x==0? y:n;
	    }
	    
	    public static void showResult( String ans ) {
	    	JOptionPane.showMessageDialog(
	    			frame, 
	    			ans, 
	    			"Answer", 
	    			JOptionPane.INFORMATION_MESSAGE
	    			);
	    }
	    
    }
    public static class Fact {
        public String question;
        public String answer;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
        public String getAnswer() {
            return question;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

}