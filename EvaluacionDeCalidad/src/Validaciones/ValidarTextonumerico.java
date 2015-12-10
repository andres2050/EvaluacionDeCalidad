/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

/**
 *
 * @author Cecilia Segura
 */

import javafx.scene.control.TextField;

public class ValidarTextonumerico extends TextField  {
    
    public ValidarTextonumerico(){
    }
    
    @Override
    public void replaceText(int i,int il,String string){
    if(string.matches("[a-zA-Z0-9 *]+$")|| string.isEmpty()){
        super.replaceText(i, il, string);
       
    } 
     
    
    }
    
    @Override
    public void replaceSelection (String string){
    super.replaceSelection(string);
    
    }
    
    
    
    
}
