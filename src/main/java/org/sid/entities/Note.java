package org.sid.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

public class Note {
@Id @GeneratedValue
Long id;
@ManyToOne()
@JoinColumn(name="etudiantId")
Appuser etudiant;
@ManyToOne()
@JoinColumn(name="QcmId")
Qcm qcm;
@ColumnDefault("0")
int reponseCorrect;
@ColumnDefault("0")
int reponseFausse;
}
