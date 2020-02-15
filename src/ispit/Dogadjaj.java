package ispit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Dogadjaj implements Comparable {
    String naziv;
    LocalDateTime pocetak;
    LocalDateTime kraj;

    public Dogadjaj(String naziv, LocalDateTime pocetak, LocalDateTime kraj) throws NeispravanFormatDogadjaja {
        if(pocetak.isAfter(kraj))throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) throws NeispravanFormatDogadjaja {
        if(pocetak.isAfter(kraj))throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        this.pocetak = pocetak;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) throws NeispravanFormatDogadjaja {
        if(kraj.isBefore(pocetak))throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        this.kraj = kraj;
    }

    public int prioritet(){
        return -2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dogadjaj dogadjaj = (Dogadjaj) o;
        return Objects.equals(getNaziv(), dogadjaj.getNaziv()) &&
                Objects.equals(getPocetak(), dogadjaj.getPocetak()) &&
                Objects.equals(getKraj(), dogadjaj.getKraj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaziv(), getPocetak(), getKraj());
    }

    @Override
    public String toString() {
        String prioritet;
        if(this instanceof DogadjajNiskogPrioriteta)prioritet = "(nizak prioritet)";
        else if(this instanceof  DogadjajSrednjegPrioriteta)prioritet = "(srednji prioritet)";
        else prioritet = "(visok prioritet)";
        return this.getNaziv()+" "+prioritet+" - početak: "+pocetak.format(DateTimeFormatter.ofPattern("dd/MM/yyyy (hh:mm)"))+", kraj: "+kraj.format(DateTimeFormatter.ofPattern("dd/MM/yyyy (HH:mm)"));
    }

    @Override
    public int compareTo(Object o) {
        if(this instanceof DogadjajVisokogPrioriteta && (o instanceof DogadjajVisokogPrioriteta || o instanceof DogadjajNiskogPrioriteta))return 1;
        if(this instanceof DogadjajSrednjegPrioriteta && (o instanceof DogadjajNiskogPrioriteta))return 1;
        if(o instanceof DogadjajVisokogPrioriteta && (this instanceof DogadjajVisokogPrioriteta || this instanceof DogadjajNiskogPrioriteta))return -1;
        if(o instanceof DogadjajSrednjegPrioriteta && (this instanceof DogadjajNiskogPrioriteta))return -1;

        return getNaziv().compareTo(((Dogadjaj)o).getNaziv());

    }
}
