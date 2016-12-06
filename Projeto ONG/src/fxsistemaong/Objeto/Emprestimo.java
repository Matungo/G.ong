package fxsistemaong.Objeto;

import java.util.Date;

/**
 *
 * @author Danilo
 */
public class Emprestimo {
    
    private int codEmprestimo, idBeneficiario;
    private java.util.Date dataRetirada;
    private java.util.Date dataDevolucao;
    private long isbn;
    private String titulo;
    
    //construtor padrão
    public Emprestimo(){
        
    }
    
    //construtor para testes
    public Emprestimo(long isbn, String titulo, Date retirada, Date devolucao){
        this.isbn = isbn;
        this.titulo = titulo;
        this.dataRetirada = retirada;
        this.dataDevolucao = devolucao;
    }

    public int getCodEmprestimo() {
        return codEmprestimo;
    }

    public void setCodEmprestimo(int codEmprestimo) {
        this.codEmprestimo = codEmprestimo;
    }

    public java.util.Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(java.util.Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public java.util.Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(java.util.Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }
    
}
