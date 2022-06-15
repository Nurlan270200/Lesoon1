import java.util.Random;

public class Main {
    public static int bossHealth = 700;

    public static int bossDamage = 50;

    public static String bossDefence;

    public static int[] heroesHealth = {270,260,250,220};
    public static int[] heroesDamage = {15,20,25,0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medical"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }
    public static void medicHeal() {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 3) {
                    continue;
                }
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    heroesHealth[i] += 50;
                }
                System.out.println(" Medic healed " + heroesAttackType[i]);
                break;
            }
        }




    public static void  playRound() {
        roundNumber++;
        chooseDefence();
        bossHit();
        heroesHit();
        printStatistics();
        medicHeal();
    }
     public static void  chooseDefence () {
         Random random = new Random();
         int randomIndex = random.nextInt(heroesAttackType.length);
         bossDefence = heroesAttackType[randomIndex];
         System.out.println("Boss chose: " + bossDefence);
     }
    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
           if (heroesHealth[i] > 0) {
               if (heroesHealth[i] - bossDamage < 0) {
                   heroesHealth[i] = 0;
               } else {
                   heroesHealth[i] = heroesHealth[i] - bossDamage;
               }
           }
        }
    }
    public static void heroesHit() {
        for (int i = 0; i <heroesDamage.length ; i++) {
            if (heroesHealth[i] > 0 && bossHealth>0) {
                if (heroesAttackType[i] == bossDefence){
                    Random random = new Random();
                    int coeff = random.nextInt(9)+2 ;
                    int critical_damage = coeff * heroesDamage[i];
                    if (bossHealth-critical_damage <0) {
                        bossHealth = 0;
                    } else
                        bossHealth= bossHealth - critical_damage;
                }
                if (bossHealth-heroesDamage [i] < 0) {
                    bossHealth=0 ;
                }
                bossHealth = bossHealth - heroesDamage[i];
            }
        }
    }
    public static boolean isGameFinished() {
        if (bossHealth <=0) {
            System.out.println("heroes won!!!");
            return true;
        }
        /*
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;

        }
        return  false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i <heroesHealth.length ; i++) {
            if (heroesHealth[i] >0) {
                allHeroesDead=false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
public static void printStatistics() {
        if (roundNumber == 0) {
            System.out.println("BEFORE GAME STARTED ___________");
        } else {
            System.out.println(roundNumber + " ROUND ___________");
        }
    System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
    for (int i = 0; i <heroesHealth.length ; i++) {
        System.out.println(heroesAttackType[i] + " health: " + heroesHealth [i]
                + " [" + bossDamage + "]");
    }
}

}