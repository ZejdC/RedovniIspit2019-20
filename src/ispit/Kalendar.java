package ispit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kalendar implements Pretrazivanje{
    List<Dogadjaj> dogadjaji;

    public Kalendar() {
        dogadjaji = new ArrayList<>();
    }


    public void zakaziDogadjaj(Dogadjaj dogadjaj) {
        dogadjaji.add(dogadjaj);
    }

    public List<Dogadjaj> dajKalendar() {
        return dogadjaji;
    }

    public void zakaziDogadjaje(List<Dogadjaj> dog) {
        dogadjaji.addAll(dog);
    }


    public void otkaziDogadjaj(Dogadjaj dog) {
        dogadjaji.remove(dog);
    }

    public void otkaziDogadjaje(List<Dogadjaj> dog) {
        dogadjaji.removeAll(dog);
    }

    public void otkaziDogadjaje(Predicate<Dogadjaj> uslov){
        dogadjaji.removeIf(uslov);
    }

    public Map<LocalDate, List<Dogadjaj>> dajKalendarPoDanima() {
        Map<LocalDate,List<Dogadjaj>> vrati = new HashMap<>();
        for(Dogadjaj d: dogadjaji){
            if(vrati.containsKey(d.getPocetak().toLocalDate())){
                vrati.get(d.getPocetak().toLocalDate()).add(d);
            }
            else{
                vrati.put(d.getPocetak().toLocalDate(),new ArrayList<>());
                vrati.get(d.getPocetak().toLocalDate()).add(d);
            }
        }
        return vrati;
    }

    public Dogadjaj dajSljedeciDogadjaj(LocalDateTime of) {
        for(Dogadjaj d: dogadjaji){
            if(of.isBefore(d.getPocetak())){
                return d;
            }
        }
        throw new IllegalArgumentException("Nemate događaja nakon navedenog datuma");
    }

    @Override
    public List<Dogadjaj> filtrirajPoKriteriju(Predicate<Dogadjaj> uslov) {
        return dogadjaji.stream().filter(uslov).collect(Collectors.toList());
    }

    @Override
    public List<Dogadjaj> dajDogadjajeZaDan(LocalDateTime ldt) {
        List<Dogadjaj> vrati = new ArrayList<>();
        for(Dogadjaj d: dogadjaji){
            if(ldt.toLocalDate().equals(d.getPocetak().toLocalDate()))vrati.add(d);
        }
        return vrati;
    }

    @Override
    public List<Dogadjaj> dajSortiraneDogadjaje(BiFunction<Dogadjaj, Dogadjaj,Integer> uslov) {
        return dogadjaji.stream().sorted((d1,d2)-> uslov.apply(d1,d2)).collect(Collectors.toList());
    }

    @Override
    public Set<Dogadjaj> dajSortiranePoPrioritetu() {
        TreeSet<Dogadjaj> vrati = new TreeSet<>();
        vrati.addAll(dogadjaji);
        return vrati;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime ldt) {
        for(Dogadjaj d: dogadjaji){
            if(d.getPocetak().isBefore(ldt)&&d.getKraj().isAfter(ldt)||ldt.equals(d.getPocetak())||ldt.equals(d.getKraj()))return false;
        }
        return true;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime ldt1, LocalDateTime ldt2) {
        if(ldt1.isAfter(ldt2))throw new IllegalArgumentException("Neispravni podci o početku i kraju");
        for(Dogadjaj d: dogadjaji){
            if(d.getKraj().isAfter(ldt1)&&d.getPocetak().isBefore(ldt2))return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String vrati = "";
        for(Dogadjaj d: dogadjaji){
            vrati+=d+"\n";
        }
        vrati = vrati.substring(0,vrati.length()-1);
        return vrati;
    }
}
