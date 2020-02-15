package ispit;

import java.time.LocalDateTime;

public class DogadjajVisokogPrioriteta extends Dogadjaj {
    public DogadjajVisokogPrioriteta(String s, LocalDateTime of, LocalDateTime of1) throws NeispravanFormatDogadjaja {
        super(s,of,of1);
    }
    public int prioritet(){
        return 1;
    }
}
