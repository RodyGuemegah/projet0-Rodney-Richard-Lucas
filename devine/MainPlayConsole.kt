 package org.sio.slam.devine

import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Jeu
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.enum.getCouleurCarteFromString
import org.sio.slam.devine.enum.getNomCarteFromString
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes

 fun main(args: Array<String>) {

     println("Avez vous besoin d'aide ?")
     val  reponse = readLine() + ""
     var aide: Boolean

     aide = reponse=="oui"

     var paqueDeCartes = Paquet()
     do {
    println("Quelles paquets voulez-vous: 32 ou 52 cartes ?")

     var demandeCarte = readLine() + ""
     if (demandeCarte == "32") {
         paqueDeCartes = Paquet(createJeu32Cartes())
         println("Création d'un paquet de 32 cartes")
     }
     else if (demandeCarte == "52") {
         println("Création d'un paquet de 52 cartes")
         var paqueDeCarte = Paquet(createJeu52Cartes())
     }else{
         println("Nous n'avons pas de paquets a proposée $demandeCarte carte ")
     }
     }while (demandeCarte != "32" && demandeCarte != "52")



    println(" ==== Instanciation du jeu, début de la partie. ====")
    val jeu = Jeu(aide, paqueDeCartes)

    println("Entrez un nom de carte dans le jeu (exemples : Roi, sept, six, As...) :")
    // TODO (optionnel) permettre de saisir un chiffre au lieu d'une chaine : 7 au lieu de Sept...
    val nomCarteUserStr: String = readLine() + ""
    val nomCarteUser: NomCarte? = getNomCarteFromString(nomCarteUserStr.trim().uppercase())

    println("Entrez un nom de \"couleur\" de carte parmi : Pique, Trefle, Coeur, Carreau : ")
    val couleurCarteUserStr: String = readLine() + ""
    val couleurCarteUser: CouleurCarte? = getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase())

    if (nomCarteUser != null && couleurCarteUser != null) {
        // prise en compte de la carte du joueur
        val carteDuJoueur: Carte = Carte(nomCarteUser, couleurCarteUser)

        if (jeu.isMatch(carteDuJoueur)) {
            println("Bravo, vous avez trouvé la bonne carte !")
        } else {
            println("Ce n'est pas la bonne carte !")
            println("votre proposition  : $carteDuJoueur")
            if (aide) {
                if (carteDuJoueur < jeu.carteADeviner) {
                    println(" vous carte est inférieur à la carte à trouver")
                } else if (carteDuJoueur > jeu.carteADeviner) {
                    println(" vous carte est inférieur à la carte à trouver")
                } else {
                    println("vous êtes sur la bonne piste")
                }
                println("Voulez vous connaitre la carte à deviner ?")
                val laCarte: String = (readLine() + "").uppercase()
                if (laCarte == "OUI") {
                    println("La carte à deviner était ${jeu.carteADeviner}")


                }


    } else {
        // utilisateur a mal renseigné sa carte
        val nomCarte = if (nomCarteUserStr == "") "?" else nomCarteUserStr
        val couleurCarte = if (couleurCarteUserStr == "") "?" else couleurCarteUserStr

        println("Désolé, mauvaise définition de carte ! (${nomCarte} de ${couleurCarte})")
    }

    // TODO (A) permettre au joueur de retenter une autre carte (sans relancer le jeu) ou d'abandonner la partie
            println("Voulez-vous réassayer")
            var reessayer = false
            val reponse = readLine()  +""
            if (reponse == "OUI") {
                reessayer = true
            }
            else {
                println("=== Fin prématurée de la partie ===")
            }

    // TODO (A) Présenter à la fin la carte à deviner
    println(jeu.carteADeviner)

    }

    // TODO (challenge-4) la stratégie de jeu est à implémenter... à faire lorsque les autres TODOs auront été réalisés
    println("Votre stratégie de jeu : ${jeu.strategiePartie()} ")

    println(" ==== Fin de la partie. ====")
}}


