package sda.spring.rest.model.dto;

public class UserDto {

    private Long id;
    private String imie;
    private String nazwisko;
    private Integer nrtelefonu;
    private String haslo;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getNrtelefonu() {
        return nrtelefonu;
    }

    public void setNrtelefonu(Integer nrtelefonu) {
        this.nrtelefonu = nrtelefonu;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
