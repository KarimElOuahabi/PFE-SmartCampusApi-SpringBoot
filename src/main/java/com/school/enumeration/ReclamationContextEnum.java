package com.school.enumeration;

public enum ReclamationContextEnum {
    ReevaluationNote("Réclamation pour réévaluation de note"),
    AccesCopiesCorrigees("Demande d'accès aux copies corrigées"),
    SupportsCoursManquants("Réclamation concernant des supports de cours manquants"),
    ProblemeMaterielLabo("Plainte concernant le manque d'équipement ou l'accès au laboratoire"),
    RessourcesBibliotheque("Réclamation concernant l'indisponibilité des ressources de la bibliothèque"),
    ComportementEnseignant("Plainte concernant le comportement ou la qualité d’enseignement du professeur"),
    PlanificationIrreguliere("Réclamation concernant une planification irrégulière des cours"),
    AnnulationCours("Plainte concernant les annulations de cours sans préavis"),
    ModificationEmploiTemps("Demande de modification de l’emploi du temps"),
    ConflitExamens("Plainte concernant des conflits dans le calendrier des examens"),
    ErreurDossierEtudiant("Réclamation concernant des erreurs dans les dossiers étudiants"),
    ProblemeInfrastructure("Plainte concernant les installations des salles ou du campus"),
    ProblemeWiFiPortail("Réclamation concernant le Wi-Fi ou les problèmes sur le portail en ligne");

    private final String description;

    ReclamationContextEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

