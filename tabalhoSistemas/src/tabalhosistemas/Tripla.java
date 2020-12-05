/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabalhosistemas;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author br
 */
public class Tripla {
    
    private Long ver;
    private Long ts;
    private Byte[] data;

    public Tripla(Long ver, Long ts, Byte[] data) {
        this.ver = ver;
        this.ts = ts;
        this.data = data;
    }

    public Long getVer() {
        return ver;
    }

    public void setVer(Long ver) {
        this.ver = ver;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ts);
        hash = 37 * hash + Arrays.hashCode(this.data);
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
        final Tripla other = (Tripla) obj;
        if (!Objects.equals(this.ts, other.ts)) {
            return false;
        }
        if (!Arrays.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tripla{" + "ver=" + ver + ", ts=" + ts + ", data=" + data + '}';
    }    
    
}
