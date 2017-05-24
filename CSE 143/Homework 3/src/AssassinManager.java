// Hung Auduong
// 4/22/16
// CSE 143 AH
// TA: Radu Cracut
// Assignment #3
//
// This class is used to play an Assassin game in which it tracks who is being
// stalked by who and who is being killed.

import java.util.*;

public class AssassinManager {
   private AssassinNode frontKill;
   private AssassinNode frontGrave;
   
   // pre: name.size() > 0. if not, (throws IllegalArgumentException)
   // post: constructs Assassin Manager object. it adds the name from the list
   //       to the kill ring in the same order.
   public AssassinManager(ArrayList<String> names) {
      if (names.size() == 0) {
         throw new IllegalArgumentException();
      }
      frontKill = new AssassinNode(names.get(0));
      frontGrave = null;
      AssassinNode temp = frontKill;
      for (int i = 1; i < names.size(); i++) {
         temp.next = new AssassinNode(names.get(i));
         temp = temp.next;
      }
   }
   
   // post: prints the name of the player that are still in the killring
   //       and who they are stalking. if there's only one player then it will
   //       say that player is stalking himself.
   public void printKillRing() {
      if (frontKill.next == null) {
         System.out.println("  " + frontKill.name + " is stalking " + frontKill.name);
      } else {
         AssassinNode last = frontKill;
         while (last.next != null) {
            System.out.println("  " + last.name + " is stalking " + last.next.name);
            last = last.next;
         }
         System.out.println("  " + last.name + " is stalking " + frontKill.name);
      }
   }
   
   // post: prints the name of the player who were assassinated and who they were
   //       assassinated by.
   public void printGraveyard() {
      AssassinNode last = frontGrave;
      while (last != null) {
         System.out.println("  " + last.name + " was killed by " + last.killer);
         last = last.next;
      }
   }
   
   // post: return if true if the given name is found in the kill ring. it checks each
   //		node if it contains the name. if it cant find it, then it returns false
   public boolean killRingContains(String name) {
      AssassinNode check = frontKill;
      return checkContain(check, name);
   }
   
   // post: returns true if the name is found in the graveyard. it checks checks each
   //		node in the graveyard if it contians the name. if not, then return false
   public boolean graveyardContains(String name) {
      AssassinNode check = frontGrave;
      return checkContain(check, name);
   }
   
   // post: private helper method that is used to check both the killRing and
   //		grave yard if it contains the name that is passed in the parameter.
   //		return true if the name is found false otherwise
   private boolean checkContain (AssassinNode check, String name) {
      while (check != null) {
         if (check.name.equalsIgnoreCase(name)) {
            return true;
         }
         check = check.next;
      }
      return false;
   }
   
   // post: return true if the game is over (1 person in the kill ring) return false otherwise.
   public boolean isGameOver() {
      return frontKill.next == null;
   }
   
   // post: return the name of the winner of the game, null if game isnt over.
   public String winner() {
      if (isGameOver()) {
         return frontKill.name;
      }
      return null;
   }
   
   // pre: if name is not part of the current kill ring (throw IllegalArgumentException)
   //      or if the game is not over throw (illegalStateException)
   // post: record the killing of the person with the given name. transferring the person
   //       from kill ring to graveyard. operation does not change the kill order. unless
   //       its the first person getting killed.
   public void kill(String name) {
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      if (isGameOver()) {
         throw new IllegalStateException();
      }
      AssassinNode kill = frontKill;
      AssassinNode prev = frontKill;
      if (frontKill.name.equalsIgnoreCase(name)) {
         while (prev.next != null) {
            prev = prev.next;
         }
         kill.killer = prev.name;
         frontKill = frontKill.next;
      } else {
         while (kill.next != null && !kill.name.equalsIgnoreCase(name)) {
            prev = kill;
            kill = kill.next;
         }
         kill.killer = prev.name;
         prev.next = kill.next;
      }
      kill.next = frontGrave;
      frontGrave = kill;
   }
}