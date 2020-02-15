package ispit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Pretrazivanje {
    public List<Dogadjaj> filtrirajPoKriteriju(Predicate<Dogadjaj> uslov);
    public List<Dogadjaj> dajDogadjajeZaDan(LocalDateTime ldt);
    public List<Dogadjaj> dajSortiraneDogadjaje(BiFunction<Dogadjaj,Dogadjaj, Integer> uslov);
    public Set<Dogadjaj> dajSortiranePoPrioritetu();
    boolean daLiSamSlobodan(LocalDateTime ldt);
    boolean daLiSamSlobodan(LocalDateTime ldt1, LocalDateTime ldt2);
}
