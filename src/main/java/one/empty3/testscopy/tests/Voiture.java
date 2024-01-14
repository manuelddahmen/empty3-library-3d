/*
 *
 *  * Copyright (c) 2024. Manuel Daniel Dahmen
 *  *
 *  *
 *  *    Copyright 2024 Manuel Daniel Dahmen
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package one.empty3.testscopy.tests;

import one.empty3.library.RepresentableConteneur;

public class Voiture extends RepresentableConteneur {
        RoueVoiture roueVoiture;
        Chassis chassis;
        Coque coque ;
        PorteAvant porteAvant = new PorteAvant();
        PorteArriere porteArrieres = new PorteArriere();
        Coffre coffre = new Coffre();
        PhareAvant phareGros = new PhareAvant();
        PhareArriere phareArriere =  new PhareArriere();
        SiegeAvant siegeAvant = new SiegeAvant();
        SiegeArriere siegeArriere = new SiegeArriere();
        PareChoc pareChoc = new PareChoc();
        PareBrise pareBrise =  new PareBrise();
        Mecanique mecanique = new Mecanique();
        private double largeur = 280.0;
        private double longueur = 600.0;
        private double espacementRoues = 300.;
        private double rayonRoue = 30.;

        public Voiture() {
            super();
            chassis = new Chassis(this);
            roueVoiture = new RoueVoiture(this);
            coque = new Coque(this);
            add(chassis);
            add(roueVoiture);
            add(coque);
        }

        public double getLargeur() {
            return largeur;
        }

        public double getLongueur() {
                return longueur;
        }

        public double getEspacementRoues() {
                return espacementRoues;
        }

        public double getEpaisseurRoue() {
                return 40;
        }

        public Chassis getChassis() {
                return chassis;
        }

        public void setChassis(Chassis chassis) {
                this.chassis = chassis;
        }

        public double getRayonRoue() {
                return rayonRoue;
        }

        public RoueVoiture getRoueVoiture() {
                return roueVoiture;
        }

        public void setRoueVoiture(RoueVoiture roueVoiture) {
                this.roueVoiture = roueVoiture;
        }

        public Coque getCoque() {
                return coque;
        }

        public void setCoque(Coque coque) {
                this.coque = coque;
        }

        public PorteAvant getPorteAvant() {
                return porteAvant;
        }

        public void setPorteAvant(PorteAvant porteAvant) {
                this.porteAvant = porteAvant;
        }

        public PorteArriere getPorteArrieres() {
                return porteArrieres;
        }

        public void setPorteArrieres(PorteArriere porteArrieres) {
                this.porteArrieres = porteArrieres;
        }

        public Coffre getCoffre() {
                return coffre;
        }

        public void setCoffre(Coffre coffre) {
                this.coffre = coffre;
        }

        public PhareAvant getPhareGros() {
                return phareGros;
        }

        public void setPhareGros(PhareAvant phareGros) {
                this.phareGros = phareGros;
        }

        public PhareArriere getPhareArriere() {
                return phareArriere;
        }

        public void setPhareArriere(PhareArriere phareArriere) {
                this.phareArriere = phareArriere;
        }

        public SiegeAvant getSiegeAvant() {
                return siegeAvant;
        }

        public void setSiegeAvant(SiegeAvant siegeAvant) {
                this.siegeAvant = siegeAvant;
        }

        public SiegeArriere getSiegeArriere() {
                return siegeArriere;
        }

        public void setSiegeArriere(SiegeArriere siegeArriere) {
                this.siegeArriere = siegeArriere;
        }

        public PareChoc getPareChoc() {
                return pareChoc;
        }

        public void setPareChoc(PareChoc pareChoc) {
                this.pareChoc = pareChoc;
        }

        public PareBrise getPareBrise() {
                return pareBrise;
        }

        public void setPareBrise(PareBrise pareBrise) {
                this.pareBrise = pareBrise;
        }

        public Mecanique getMecanique() {
                return mecanique;
        }

        public void setMecanique(Mecanique mecanique) {
                this.mecanique = mecanique;
        }

        public void setLargeur(double largeur) {
                this.largeur = largeur;
        }

        public void setLongueur(double longueur) {
                this.longueur = longueur;
        }

        public void setEspacementRoues(double espacementRoues) {
                this.espacementRoues = espacementRoues;
        }

        public void setRayonRoue(double rayonRoue) {
                this.rayonRoue = rayonRoue;
        }

        public Double getHauteurPorte() {
                return 400.;
        }

        public double getHauteurBasCaisse() {
                return 20.;
        }

        public double getHauteurCoffre() {
                return 200;
        }
}
