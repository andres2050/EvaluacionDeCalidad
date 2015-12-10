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

public class ValidarCorreo extends TextField  {
    
    public ValidarCorreo(){
    }
    
    @Override
    public void replaceText(int i,int il,String string){
    if(string.matches("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$")|| string.isEmpty()){
        super.replaceText(i, il, string);
       
    } 
     
    
    }
    
    @Override
    public void replaceSelection (String string){
    super.replaceSelection(string);
    
    }
    
    
    
    
}
