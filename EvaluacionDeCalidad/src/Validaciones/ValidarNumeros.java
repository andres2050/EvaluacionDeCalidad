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

public class ValidarNumeros extends TextField {
    
    public ValidarNumeros(){
       this.setPromptText("Entra solo numeros");
    }
    
    
    @Override
    public void  replaceText(int i, int il, String string){
    if(string.matches("[0-9]") || string.isEmpty()){
    super.replaceText(i, il, string);
    
    }
      
    }
    
     @Override
     public void replaceSelection(String string){
         super.replaceSelection(string);
     }
    
}
