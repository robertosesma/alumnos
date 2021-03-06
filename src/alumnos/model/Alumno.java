
package alumnos.model;

public class Alumno {
    private String n;
    private String periodo;
    private String curso;
    private String grupo;
    private String dni;
    private String pc;
    private Boolean fijo;
    private String name;
    private String clase;
    private String pec1;
    private String pec;
    private String nota;
    private String coment;
    private Boolean changed;
    private Boolean copia;
    private String idcopia;
    private Boolean entregaPEC1;
    private Boolean entregaPEC;
    
    public Alumno() {
        this.changed = false;
    }
    
    public String getN() {
        return this.n;
    }

    public void setN(String c) {
        this.n = c;
    }
    
    public String getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(String c) {
        this.periodo = c;
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String c) {
        this.curso = c;
    }

    public String getGrupo() {
        return this.grupo;
    }

    public void setGrupo(String c) {
        this.grupo = c;
    }
    
    public String getDNI() {
        return this.dni;
    }

    public void setDNI(String c) {
        this.dni = c;
    }

    public String getPC() {
        return this.pc;
    }

    public void setPC(String c) {
        this.pc = c;
    }
    
    public Boolean getFijo() {
        return this.fijo;
    }

    public void setFijo(Boolean l) {
        this.fijo = l;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String c) {
        this.name = c;
    }
    
    public String getClase() {
        return this.clase;
    }

    public void setClase(String c) {
        this.clase = c;
    }

    public String getPEC1() {
        return this.pec1;
    }

    public void setPEC1(String c) {
        this.pec1 = c;
    }

    public String getPEC() {
        return this.pec;
    }

    public void setPEC(String c) {
        this.pec = c;
    }
    
    public String getNOTA() {
        return this.nota;
    }

    public void setNOTA(String c) {
        this.nota = c;
    }

    public String getComent() {
        return this.coment;
    }

    public void setComent(String c) {
        this.coment = c;
    }

    public Boolean getChanged() {
        return this.changed;
    }

    public void setChanged(Boolean l) {
        this.changed = l;
    }

    public Boolean getCopia() {
        return this.copia;
    }

    public void setCopia(Boolean l) {
        this.copia = l;
    }

    public void setIDCopia(String c) {
        this.idcopia = c;
    }

    public String getIDCopia() {
        return this.idcopia;
    }

	public Boolean getEntregaPEC1() {
		return this.entregaPEC1;
	}

	public void setEntregaPEC1(Boolean l) {
		this.entregaPEC1 = l;
	}
	
	public Boolean getEntregaPEC() {
		return this.entregaPEC;
	}

	public void setEntregaPEC(Boolean l) {
		this.entregaPEC = l;
	}
}
