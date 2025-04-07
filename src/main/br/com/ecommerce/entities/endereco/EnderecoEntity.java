package br.com.ecommerce.entities.endereco;
import br.com.ecommerce.entities.cliente.PessoaEntity;
import java.util.Set;
import javax.persistence.*;

@Entity(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rua",nullable = false)
    private String rua;

    @Column(name = "bairro",nullable = false)
    private String bairro;

    @Column(name = "municipio" ,nullable = false)
    private String  municipio;

    @Column(name = "estado" ,nullable = false)
    private String  estado;

    @Column(name = "cep" ,nullable = false)
    private String cep;

    public EnderecoEntity(Long id, String rua, String bairro, String municipio, String estado, String cep) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
    }

    @ManyToMany(mappedBy = "enderecos")
    private Set<PessoaEntity> pessoas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
