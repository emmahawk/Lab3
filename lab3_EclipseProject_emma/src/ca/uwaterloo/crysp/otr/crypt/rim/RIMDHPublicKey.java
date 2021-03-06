/*
 *  Java OTR library
 *  Copyright (C) 2008-2009  Ian Goldberg, Muhaimeen Ashraf, Andrew Chung,
 *                           Can Tang
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of version 2.1 of the GNU Lesser General
 *  Public License as published by the Free Software Foundation.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package ca.uwaterloo.crysp.otr.crypt.rim;

import net.rim.device.api.crypto.DHPublicKey;
import net.rim.device.api.crypto.DHCryptoSystem;

/**
 * Public key for the Diffie Hellman key exchange. Consists of a single
 * BigInteger value.
 * 
 */
public class RIMDHPublicKey extends RIMDHKey implements
        ca.uwaterloo.crysp.otr.crypt.DHPublicKey {
    private DHPublicKey pub;

    public RIMDHPublicKey(ca.uwaterloo.crysp.otr.crypt.MPI val) {
        super(RIMDHKeyPairGenerator.DH_G, RIMDHKeyPairGenerator.DH_P);
        try {
            DHCryptoSystem dhcs = super.getDHCS();
           byte[] v = val.getValue();
            pub = new DHPublicKey(dhcs, v);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public RIMDHPublicKey(DHPublicKey val) {
        super(RIMDHKeyPairGenerator.DH_G, RIMDHKeyPairGenerator.DH_P);
        pub = val;
    }

    public byte[] getY() {
        try {
            return RIMMPI.toBytes(pub.getPublicKeyData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DHPublicKey getDHPublicKey() {
        return pub;
    }

    public byte[] serialize() {
        return getY();
    }

    public String toString() {
        return pub.toString();
    }
}
