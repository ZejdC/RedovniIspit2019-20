package ispit;

import java.time.LocalDateTime;

public class DogadjajSrednjegPrioriteta extends Dogadjaj {
    public DogadjajSrednjegPrioriteta(String naziv, LocalDateTime pocetak, LocalDateTime kraj) throws NeispravanFormatDogadjaja {
        super(naziv,pocetak,kraj);
    }
    public int prioritet(){
        return 0;
    }
}
