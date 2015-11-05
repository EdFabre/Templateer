/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateer;

import drupaltemplateer.EnclosingBox;
import org.apache.commons.lang.ArrayUtils;

/**
 * Class Encloser extends EnclosingBox, and is designed, not to be initiated, 
 * but to used as an enclosement for holding all of the accordion folds.
 * Only needed for entire accordion setup.
 * 
 * @author rlsokel
 */
public class Encloser extends EnclosingBox {

    public static String[] encloseJob(
            String[] descInput,
            String[] reqInput,
            String[] contactInput) {
        
        String[] descEnclosing = {
            "<p><strong>Description</strong></p>",
            "<p>",  // Things go here.
            "</p>"};
        
        String[] reqEnclosing = {
            "<p>&nbsp;</p><p><strong>Requirements</strong></p><ul>",  // Stuff here.
            "</ul>"};
        
        String[] contactEnclosing = {
            "<p><strong>More Info</strong></p>",
            "<p>",  // Stuff here.
            "</p>"
        };
        int dInt = lineCounter(descInput);
        int dEnInt = lineCounter(descEnclosing);
        int rInt = lineCounter(reqInput);
        int rEnInt = lineCounter(reqEnclosing);
        int cInt = lineCounter(contactInput);
        int cEnInt = lineCounter(contactEnclosing);
        int descLineCount = lineCounter(descEnclosing);
        
        String[] dComplete = new String[dInt + dEnInt];
        String[] rComplete = new String[rInt + rEnInt];
        String[] cComplete = new String[cInt + cEnInt];
        
        for (int i=0; i<dComplete.length; i++) {
            
            if(i<2)
                dComplete[i] = descEnclosing[i];
            if(i>=2 && i<(dComplete.length-1))
                dComplete[i] = descInput[i-(dEnInt-1)];
            if(i==(dComplete.length-1))
                dComplete[i] = descEnclosing[dEnInt-1];
        }
        for (int i=0; i<rComplete.length; i++) {
            
            if(i==0)
                rComplete[i] = reqEnclosing[i];
            if(i>0 && i<(rComplete.length-1)) 
                rComplete[i] = "<li>" + reqInput[i-(rEnInt-1)] + "</li>";
            if(i==(rComplete.length-1))
                rComplete[i] = reqEnclosing[rEnInt-1];
        }
        for (int i=0; i<cComplete.length; i++) {
            
            if(i<2)
                cComplete[i] = contactEnclosing[i];
            if(i>=2 && i<(cComplete.length-1))
                cComplete[i] = contactInput[i-(cEnInt-1)];
            if(i==(cComplete.length-1))
                cComplete[i] = contactEnclosing[cEnInt-1];
        }
        
        String[] enclosedHTML = (String[])ArrayUtils.addAll(dComplete, rComplete);
        return (String[])ArrayUtils.addAll(enclosedHTML, cComplete);
    }
    
}
