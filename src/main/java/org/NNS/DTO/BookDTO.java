package org.NNS.DTO;



public class BookDTO {

    private String name;
    private String ISBN;

    public BookDTO() {
    }

    public BookDTO(String name, String ISBN) {
        this.name = name;
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "name='" + name + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}