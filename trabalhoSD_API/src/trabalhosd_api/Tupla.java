/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd_api;

import java.util.Objects;

/**
 *
 * @author br
 */
public class Tupla {
    
    private String e;
    private Tripla result;
    
    public Tupla (String e){
        this.e = e;
    }
    
    public Tupla(String e, Tripla result) {
        this(e);
        this.result = result;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public Tripla getResult() {
        return result;
    }

    public void setResult(Tripla result) {
        this.result = result;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.e);
        hash = 71 * hash + Objects.hashCode(this.result);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tupla other = (Tupla) obj;
        if (!Objects.equals(this.e, other.e)) {
            return false;
        }
        return Objects.equals(this.result, other.result);
    }
    
    
    
    
}
