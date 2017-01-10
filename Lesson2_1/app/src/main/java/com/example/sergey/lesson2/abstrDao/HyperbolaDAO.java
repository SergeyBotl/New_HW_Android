package com.example.sergey.lesson2.abstrDao;

import com.example.sergey.lesson2.entity.Hyperbola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HyperbolaDAO {

    private static HyperbolaDAO hyperbolaDAO;


    public static HyperbolaDAO getHyperbolaDAO() {
        if (hyperbolaDAO == null)
            hyperbolaDAO = new HyperbolaDAO();
        return hyperbolaDAO;
    }

    List<Hyperbola> hp = new ArrayList<>();


    public void save(Hyperbola vh) {
        hp.add(vh);
    }


    public Collection<Hyperbola> geHyperbola() {
        return hp;
    }


    public Hyperbola valueX(Hyperbola h) {
        if (h.getK() != 0) {
            return new Hyperbola(h.getK() / h.getY(), h.getY(), h.getK());
        }
        return null;
    }

    public Hyperbola valueY(Hyperbola h) {
        if (h.getK() != 0) {
            return new Hyperbola(h.getX(), h.getK() / h.getX(), h.getK());
        }
        return null;
    }
}
