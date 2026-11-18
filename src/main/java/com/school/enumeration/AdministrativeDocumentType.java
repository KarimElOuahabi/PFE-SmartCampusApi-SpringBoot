package com.school.enumeration;

import java.util.*;
import java.util.stream.*;

public enum AdministrativeDocumentType {

    // Pour Professeur
    ATTESTATION_TRAVAIL(EnumSet.of(UserType.Teacher)),
    BULLETIN_SALAIRE(EnumSet.of(UserType.Teacher)),
    DEMANDE_CONGE_ADMINISTRATIF(EnumSet.of(UserType.Teacher)),
    DEMANDE_AUTORISATION_ABSENCE(EnumSet.of(UserType.Teacher)),
    ATTESTATION_ENSEIGNEMENT(EnumSet.of(UserType.Teacher)),
    DEMANDE_BUDGET_PROF(EnumSet.of(UserType.Teacher)),

    // Pour Étudiant
    RELEVE_NOTES(EnumSet.of(UserType.Student)),
    ATTESTATION_SCOLARITE(EnumSet.of(UserType.Student)),
    RETRAIT_PROVISOIRE_BAC(EnumSet.of(UserType.Student)),
    RETRAIT_DEFINITIF_BAC(EnumSet.of(UserType.Student)),
    CARTE_ETUDIANT(EnumSet.of(UserType.Student));

    private final Set<UserType> allowedUserTypes;

    AdministrativeDocumentType(Set<UserType> allowedUserTypes) {
        this.allowedUserTypes = allowedUserTypes;
    }

    public boolean isAllowedFor(UserType userType) {
        return allowedUserTypes.contains(userType);
    }

    public static List<AdministrativeDocumentType> getAllowedFor(UserType userType) {
        return Arrays.stream(values())
                .filter(doc -> doc.isAllowedFor(userType))
                .collect(Collectors.toList());
    }
}
