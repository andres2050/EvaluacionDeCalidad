/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Estudiante;

/**
 *
 * @author Andres
 */
public class Respuestas {
    
    String Grupo;
    String pregunta;
    int respuesta;

    public Respuestas(String Grupo, String pregunta, int respuesta) {
        this.Grupo = Grupo;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public Respuestas() {
    }
    
    
    
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
    
    
    
}
