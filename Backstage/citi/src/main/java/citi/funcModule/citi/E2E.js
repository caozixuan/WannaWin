function getByteArray(c) {
    a = new Array();
    for (var b = 0; b < c.length; b++) {
        a[b] = c.charCodeAt(b)
    }
    return a
}
var dbits;
var j_lm = (((244837814094590) & 16777215) == 15715070);
function BigInteger(d, b, c) {
    if (d != null) {
        if ("number" == typeof d) {
            this.fromNumber(d, b, c)
        } else {
            if (b == null && "string" != typeof d) {
                this.fromString(d, 256)
            } else {
                this.fromString(d, b)
            }
        }
    }
}
function nbi() {
    return new BigInteger(null)
}
function am1(e, b, h, f, c, d) {
    while (--d >= 0) {
        var g = b * this[e++] + h[f] + c;
        c = Math.floor(g / 67108864);
        h[f++] = g & 67108863
    }
    return c
}
function am2(m, g, f, b, j, e) {
    var k = g & 32767,
        h = g >> 15;
    while (--e >= 0) {
        var c = this[m] & 32767;
        var l = this[m++] >> 15;
        var d = h * c + l * k;
        c = k * c + ((d & 32767) << 15) + f[b] + (j & 1073741823);
        j = (c >>> 30) + (d >>> 15) + h * l + (j >>> 30);
        f[b++] = c & 1073741823
    }
    return j
}
function am3(m, g, f, b, j, e) {
    var k = g & 16383,
        h = g >> 14;
    while (--e >= 0) {
        var c = this[m] & 16383;
        var l = this[m++] >> 14;
        var d = h * c + l * k;
        c = k * c + ((d & 16383) << 14) + f[b] + j;
        j = (c >> 28) + (d >> 14) + h * l;
        f[b++] = c & 268435455
    }
    return j
}
if (navigator.appName == "Nokia") {
    BigInteger.prototype.am = am3;
    dbits = 28
} else {
    if (j_lm && (navigator.appName == "Microsoft Internet Explorer")) {
        BigInteger.prototype.am = am2;
        dbits = 30
    } else {
        if (j_lm && (navigator.appName != "Netscape")) {
            BigInteger.prototype.am = am1;
            dbits = 26
        } else {
            if ((4294967295 == -1) && (navigator.appName == "Netscape")) {
                BigInteger.prototype.am = am1;
                dbits = 26
            } else {
                BigInteger.prototype.am = am3;
                dbits = 28
            }
        }
    }
}
BigInteger.prototype.DB = dbits;
BigInteger.prototype.DM = ((1 << dbits) - 1);
BigInteger.prototype.DV = (1 << dbits);
var BI_FP = 52;
BigInteger.prototype.FV = Math.pow(2, BI_FP);
BigInteger.prototype.F1 = BI_FP - dbits;
BigInteger.prototype.F2 = 2 * dbits - BI_FP;
var BI_RM = "0123456789abcdefghijklmnopqrstuvwxyz";
var BI_RC = new Array();
var rr, vv;
rr = "0".charCodeAt(0);
for (vv = 0; vv <= 9; ++vv) {
    BI_RC[rr++] = vv
}
rr = "a".charCodeAt(0);
for (vv = 10; vv < 36; ++vv) {
    BI_RC[rr++] = vv
}
rr = "A".charCodeAt(0);
for (vv = 10; vv < 36; ++vv) {
    BI_RC[rr++] = vv
}
function int2char(b) {
    return BI_RM.charAt(b)
}
function intAt(d, b) {
    var c = BI_RC[d.charCodeAt(b)];
    return (c == null) ? -1 : c
}
function bnpCopyTo(c) {
    for (var b = this.t - 1; b >= 0; --b) {
        c[b] = this[b]
    }
    c.t = this.t;
    c.s = this.s
}
function bnpFromInt(b) {
    this.t = 1;
    this.s = (b < 0) ? -1 : 0;
    if (b > 0) {
        this[0] = b
    } else {
        if (b < -1) {
            this[0] = b + DV
        } else {
            this.t = 0
        }
    }
}
function nbv(b) {
    var c = nbi();
    c.fromInt(b);
    return c
}
function bnpFromString(c, h) {
    var f;
    if (h == 16) {
        f = 4
    } else {
        if (h == 8) {
            f = 3
        } else {
            if (h == 256) {
                f = 8
            } else {
                if (h == 2) {
                    f = 1
                } else {
                    if (h == 32) {
                        f = 5
                    } else {
                        if (h == 4) {
                            f = 2
                        } else {
                            this.fromRadix(c, h);
                            return
                        }
                    }
                }
            }
        }
    }
    this.t = 0;
    this.s = 0;
    var d = c.length,
        g = false,
        e = 0;
    while (--d >= 0) {
        var b = (f == 8) ? c[d] & 255 : intAt(c, d);
        if (b < 0) {
            if (c.charAt(d) == "-") {
                g = true
            }
            continue
        }
        g = false;
        if (e == 0) {
            this[this.t++] = b
        } else {
            if (e + f > this.DB) {
                this[this.t - 1] |= (b & ((1 << (this.DB - e)) - 1)) << e;
                this[this.t++] = (b >> (this.DB - e))
            } else {
                this[this.t - 1] |= b << e
            }
        }
        e += f;
        if (e >= this.DB) {
            e -= this.DB
        }
    }
    if (f == 8 && (c[0] & 128) != 0) {
        this.s = -1;
        if (e > 0) {
            this[this.t - 1] |= ((1 << (this.DB - e)) - 1) << e
        }
    }
    this.clamp();
    if (g) {
        BigInteger.ZERO.subTo(this, this)
    }
}
function bnpClamp() {
    var b = this.s & this.DM;
    while (this.t > 0 && this[this.t - 1] == b) {--this.t
    }
}
function bnToString(j) {
    if (this.s < 0) {
        return "-" + this.negate().toString(j)
    }
    var h;
    if (j == 16) {
        h = 4
    } else {
        if (j == 8) {
            h = 3
        } else {
            if (j == 2) {
                h = 1
            } else {
                if (j == 32) {
                    h = 5
                } else {
                    if (j == 4) {
                        h = 2
                    } else {
                        return this.toRadix(j)
                    }
                }
            }
        }
    }
    var f = (1 << h) - 1,
        c,
        b = false,
        e = "",
        g = this.t;
    var d = this.DB - (g * this.DB) % h;
    if (g-->0) {
        if (d < this.DB && (c = this[g] >> d) > 0) {
            b = true;
            e = int2char(c)
        }
        while (g >= 0) {
            if (d < h) {
                c = (this[g] & ((1 << d) - 1)) << (h - d);
                c |= this[--g] >> (d += this.DB - h)
            } else {
                c = (this[g] >> (d -= h)) & f;
                if (d <= 0) {
                    d += this.DB; --g
                }
            }
            if (c > 0) {
                b = true
            }
            if (b) {
                e += int2char(c)
            }
        }
    }
    if (j == 16 && e.length % 2 > 0) {
        e = "0" + e
    }
    return b ? e: "0"
}
function bnNegate() {
    var b = nbi();
    BigInteger.ZERO.subTo(this, b);
    return b
}
function bnAbs() {
    return (this.s < 0) ? this.negate() : this
}
function bnCompareTo(b) {
    var c = this.s - b.s;
    if (c != 0) {
        return c
    }
    var d = this.t;
    c = d - b.t;
    if (c != 0) {
        return c
    }
    while (--d >= 0) {
        if ((c = this[d] - b[d]) != 0) {
            return c
        }
    }
    return 0
}
function nbits(b) {
    var c = 1,
        d;
    if ((d = b >>> 16) != 0) {
        b = d;
        c += 16
    }
    if ((d = b >> 8) != 0) {
        b = d;
        c += 8
    }
    if ((d = b >> 4) != 0) {
        b = d;
        c += 4
    }
    if ((d = b >> 2) != 0) {
        b = d;
        c += 2
    }
    if ((d = b >> 1) != 0) {
        b = d;
        c += 1
    }
    return c
}
function bnBitLength() {
    if (this.t <= 0) {
        return 0
    }
    return this.DB * (this.t - 1) + nbits(this[this.t - 1] ^ (this.s & this.DM))
}
function bnpDLShiftTo(c, d) {
    var b;
    for (b = this.t - 1; b >= 0; --b) {
        d[b + c] = this[b]
    }
    for (b = c - 1; b >= 0; --b) {
        d[b] = 0
    }
    d.t = this.t + c;
    d.s = this.s
}
function bnpDRShiftTo(c, d) {
    for (var b = c; b < this.t; ++b) {
        d[b - c] = this[b]
    }
    d.t = Math.max(this.t - c, 0);
    d.s = this.s
}
function bnpLShiftTo(c, g) {
    var j = c % this.DB;
    var b = this.DB - j;
    var e = (1 << b) - 1;
    var f = Math.floor(c / this.DB),
        d = (this.s << j) & this.DM,
        h;
    for (h = this.t - 1; h >= 0; --h) {
        g[h + f + 1] = (this[h] >> b) | d;
        d = (this[h] & e) << j
    }
    for (h = f - 1; h >= 0; --h) {
        g[h] = 0
    }
    g[f] = d;
    g.t = this.t + f + 1;
    g.s = this.s;
    g.clamp()
}
function bnpRShiftTo(c, f) {
    f.s = this.s;
    var e = Math.floor(c / this.DB);
    if (e >= this.t) {
        f.t = 0;
        return
    }
    var h = c % this.DB;
    var b = this.DB - h;
    var d = (1 << h) - 1;
    f[0] = this[e] >> h;
    for (var g = e + 1; g < this.t; ++g) {
        f[g - e - 1] |= (this[g] & d) << b;
        f[g - e] = this[g] >> h
    }
    if (h > 0) {
        f[this.t - e - 1] |= (this.s & d) << b
    }
    f.t = this.t - e;
    f.clamp()
}
function bnpSubTo(f, d) {
    var e = 0,
        c = 0,
        b = Math.min(f.t, this.t);
    while (e < b) {
        c += this[e] - f[e];
        d[e++] = c & this.DM;
        c >>= this.DB
    }
    if (f.t < this.t) {
        c -= f.s;
        while (e < this.t) {
            c += this[e];
            d[e++] = c & this.DM;
            c >>= this.DB
        }
        c += this.s
    } else {
        c += this.s;
        while (e < f.t) {
            c -= f[e];
            d[e++] = c & this.DM;
            c >>= this.DB
        }
        c -= f.s
    }
    d.s = (c < 0) ? -1 : 0;
    if (c < -1) {
        d[e++] = this.DV + c
    } else {
        if (c > 0) {
            d[e++] = c
        }
    }
    d.t = e;
    d.clamp()
}
function bnpMultiplyTo(f, d) {
    var b = this.abs(),
        c = f.abs();
    var e = b.t;
    d.t = e + c.t;
    while (--e >= 0) {
        d[e] = 0
    }
    for (e = 0; e < c.t; ++e) {
        d[e + b.t] = b.am(0, c[e], d, e, 0, b.t)
    }
    d.s = 0;
    d.clamp();
    if (this.s != f.s) {
        BigInteger.ZERO.subTo(d, d)
    }
}
function bnpSquareTo(d) {
    var b = this.abs();
    var e = d.t = 2 * b.t;
    while (--e >= 0) {
        d[e] = 0
    }
    for (e = 0; e < b.t - 1; ++e) {
        var c = b.am(e, b[e], d, 2 * e, 0, 1);
        if ((d[e + b.t] += b.am(e + 1, 2 * b[e], d, 2 * e + 1, c, b.t - e - 1)) >= b.DV) {
            d[e + b.t] -= b.DV;
            d[e + b.t + 1] = 1
        }
    }
    if (d.t > 0) {
        d[d.t - 1] += b.am(e, b[e], d, 2 * e, 0, 1)
    }
    d.s = 0;
    d.clamp()
}
function bnpDivRemTo(l, o, p) {
    var e = l.abs();
    if (e.t <= 0) {
        return
    }
    var n = this.abs();
    if (n.t < e.t) {
        if (o != null) {
            o.fromInt(0)
        }
        if (p != null) {
            this.copyTo(p)
        }
        return
    }
    if (p == null) {
        p = nbi()
    }
    var r = nbi(),
        u = this.s,
        m = l.s;
    var f = this.DB - nbits(e[e.t - 1]);
    if (f > 0) {
        e.lShiftTo(f, r);
        n.lShiftTo(f, p)
    } else {
        e.copyTo(r);
        n.copyTo(p)
    }
    var j = r.t;
    var t = r[j - 1];
    if (t == 0) {
        return
    }
    var k = t * (1 << this.F1) + ((j > 1) ? r[j - 2] >> this.F2: 0);
    var b = this.FV / k,
        c = (1 << this.F1) / k,
        d = 1 << this.F2;
    var g = p.t,
        h = g - j,
        q = (o == null) ? nbi() : o;
    r.dlShiftTo(h, q);
    if (p.compareTo(q) >= 0) {
        p[p.t++] = 1;
        p.subTo(q, p)
    }
    BigInteger.ONE.dlShiftTo(j, q);
    q.subTo(r, r);
    while (r.t < j) {
        r[r.t++] = 0
    }
    while (--h >= 0) {
        var s = (p[--g] == t) ? this.DM: Math.floor(p[g] * b + (p[g - 1] + d) * c);
        if ((p[g] += r.am(0, s, p, h, 0, j)) < s) {
            r.dlShiftTo(h, q);
            p.subTo(q, p);
            while (p[g] < --s) {
                p.subTo(q, p)
            }
        }
    }
    if (o != null) {
        p.drShiftTo(j, o);
        if (u != m) {
            BigInteger.ZERO.subTo(o, o)
        }
    }
    p.t = j;
    p.clamp();
    if (f > 0) {
        p.rShiftTo(f, p)
    }
    if (u < 0) {
        BigInteger.ZERO.subTo(p, p)
    }
}
function Classic(b) {
    this.m = b
}
function cConvert(b) {
    if (b.s < 0 || b.compareTo(this.m) >= 0) {
        return b.mod(this.m)
    } else {
        return b
    }
}
function cRevert(b) {
    return b
}
function cReduce(b) {
    b.divRemTo(this.m, null, b)
}
function cMulTo(b, c, d) {
    b.multiplyTo(c, d);
    this.reduce(d)
}
function cSqrTo(b, c) {
    b.squareTo(c);
    this.reduce(c)
}
Classic.prototype.convert = cConvert;
Classic.prototype.revert = cRevert;
Classic.prototype.reduce = cReduce;
Classic.prototype.mulTo = cMulTo;
Classic.prototype.sqrTo = cSqrTo;
function bnpInvDigit() {
    if (this.t < 1) {
        return 0
    }
    var b = this[0];
    if ((b & 1) == 0) {
        return 0
    }
    var c = b & 3;
    c = (c * (2 - (b & 15) * c)) & 15;
    c = (c * (2 - (b & 255) * c)) & 255;
    c = (c * (2 - (((b & 65535) * c) & 65535))) & 65535;
    c = (c * (2 - b * c % this.DV)) % this.DV;
    return (c > 0) ? this.DV - c: -c
}
function Montgomery(b) {
    this.m = b;
    this.mp = b.invDigit();
    this.mpl = this.mp & 32767;
    this.mph = this.mp >> 15;
    this.um = (1 << (b.DB - 15)) - 1;
    this.mt2 = 2 * b.t
}
function montConvert(b) {
    var c = nbi();
    b.abs().dlShiftTo(this.m.t, c);
    c.divRemTo(this.m, null, c);
    if (b.s < 0 && c.compareTo(BigInteger.ZERO) > 0) {
        this.m.subTo(c, c)
    }
    return c
}
function montRevert(b) {
    var c = nbi();
    b.copyTo(c);
    this.reduce(c);
    return c
}
function montReduce(b) {
    while (b.t <= this.mt2) {
        b[b.t++] = 0
    }
    for (var d = 0; d < this.m.t; ++d) {
        var e = b[d] & 32767;
        var c = (e * this.mpl + (((e * this.mph + (b[d] >> 15) * this.mpl) & this.um) << 15)) & b.DM;
        e = d + this.m.t;
        b[e] += this.m.am(0, c, b, d, 0, this.m.t);
        while (b[e] >= b.DV) {
            b[e] -= b.DV;
            b[++e]++
        }
    }
    b.clamp();
    b.drShiftTo(this.m.t, b);
    if (b.compareTo(this.m) >= 0) {
        b.subTo(this.m, b)
    }
}
function montSqrTo(b, c) {
    b.squareTo(c);
    this.reduce(c)
}
function montMulTo(b, c, d) {
    b.multiplyTo(c, d);
    this.reduce(d)
}
Montgomery.prototype.convert = montConvert;
Montgomery.prototype.revert = montRevert;
Montgomery.prototype.reduce = montReduce;
Montgomery.prototype.mulTo = montMulTo;
Montgomery.prototype.sqrTo = montSqrTo;
function bnpIsEven() {
    return ((this.t > 0) ? (this[0] & 1) : this.s) == 0
}
function bnpExp(d, c) {
    var e = nbi(),
        b = nbi(),
        f = c.convert(this),
        g = nbits(d) - 1;
    f.copyTo(e);
    while (--g >= 0) {
        c.sqrTo(e, b);
        if ((d & (1 << g)) > 0) {
            c.mulTo(b, f, e)
        } else {
            var h = e;
            e = b;
            b = h
        }
    }
    return c.revert(e)
}
function bnModPowInt(d, b) {
    var c;
    if (d < 256 || b.isEven()) {
        c = new Classic(b)
    } else {
        c = new Montgomery(b)
    }
    return this.exp(d, c)
}
function bnpBitwiseTo(g, c, e) {
    var f, d, b = Math.min(g.t, this.t);
    for (f = 0; f < b; ++f) {
        e[f] = c(this[f], g[f])
    }
    if (g.t < this.t) {
        d = g.s & this.DM;
        for (f = b; f < this.t; ++f) {
            e[f] = c(this[f], d)
        }
        e.t = this.t
    } else {
        d = this.s & this.DM;
        for (f = b; f < g.t; ++f) {
            e[f] = c(d, g[f])
        }
        e.t = g.t
    }
    e.s = c(this.s, g.s);
    e.clamp()
}
function op_xor(b, c) {
    return b ^ c
}
function bnXor(b) {
    var c = nbi();
    this.bitwiseTo(b, op_xor, c);
    return c
}
function lbit(b) {
    if (b == 0) {
        return - 1
    }
    var c = 0;
    if ((b & 65535) == 0) {
        b >>= 16;
        c += 16
    }
    if ((b & 255) == 0) {
        b >>= 8;
        c += 8
    }
    if ((b & 15) == 0) {
        b >>= 4;
        c += 4
    }
    if ((b & 3) == 0) {
        b >>= 2;
        c += 2
    }
    if ((b & 1) == 0) {++c
    }
    return c
}
BigInteger.prototype.copyTo = bnpCopyTo;
BigInteger.prototype.fromInt = bnpFromInt;
BigInteger.prototype.fromString = bnpFromString;
BigInteger.prototype.clamp = bnpClamp;
BigInteger.prototype.dlShiftTo = bnpDLShiftTo;
BigInteger.prototype.subTo = bnpSubTo;
BigInteger.prototype.rShiftTo = bnpRShiftTo;
BigInteger.prototype.drShiftTo = bnpDRShiftTo;
BigInteger.prototype.invDigit = bnpInvDigit;
BigInteger.prototype.isEven = bnpIsEven;
BigInteger.prototype.multiplyTo = bnpMultiplyTo;
BigInteger.prototype.lShiftTo = bnpLShiftTo;
BigInteger.prototype.divRemTo = bnpDivRemTo;
BigInteger.prototype.squareTo = bnpSquareTo;
BigInteger.prototype.exp = bnpExp;
BigInteger.prototype.bitwiseTo = bnpBitwiseTo;
BigInteger.prototype.toString = bnToString;
BigInteger.prototype.negate = bnNegate;
BigInteger.prototype.abs = bnAbs;
BigInteger.prototype.compareTo = bnCompareTo;
BigInteger.prototype.bitLength = bnBitLength;
BigInteger.prototype.modPowInt = bnModPowInt;
BigInteger.prototype.xor = bnXor;
BigInteger.ZERO = nbv(0);
BigInteger.ONE = nbv(1);
function parseBigInt(c, b) {
    return new BigInteger(c, b)
}
function pkcs1pad2B(h, c) {
    var d = h.length;
    if (d > c - 11 - 4) {
        throw "104"
    }
    var b = [0, 2, 255, 255, 255, 255];
    var j = c - d - 3 - 4;
    var e = randomBytes(j);
    var g = b.concat(e, [0], h);
    var f = new BigInteger(g);
    return f
}
function randomBytes(c) {
    var b = [];
    var d = 0;
    for (d = 0; d < c; d++) {
        b[d] = Math.ceil(Math.random() * 255)
    }
    return b
}
function pkcs1pad2(j, e) {
    var f = Math.ceil(j.bitLength() / 8);
    if (e < f + 11 + 4) {
        alert("Message too long for RSA");
        return null
    }
    var k = [0, 2, 255, 255, 255, 255];
    var d;
    d = e - f - 7;
    var h = 0;
    var b = 6;
    while (b < d + 6) {
        h = 0;
        while (h == 0) {
            h = Math.floor(Math.random() * 255)
        }
        k[b++] = h
    }
    var g = new BigInteger(k);
    var c = g.toString(16) + "00" + j.toString(16);
    return new BigInteger(c, 16)
}
function RSAKey() {
    this.n = null;
    this.e = 0;
    this.d = null
}
RSAKey.prototype.setPublic = function(c, b) {
    if (c != null && b != null && c.length > 0 && b.length > 0) {
        this.n = parseBigInt(c, 16);
        this.e = parseInt(b, 16)
    } else {
        alert("Invalid RSA public key")
    }
};
RSAKey.prototype.doPublic = function(b) {
    return b.modPowInt(this.e, this.n)
};
RSAKey.prototype.encrypt = function(e) {
    var b = pkcs1pad2(e, (this.n.bitLength() + 7) >> 3);
    if (b == null) {
        return null
    }
    var c = this.doPublic(b);
    if (c == null) {
        return null
    }
    var d = c.toString(16);
    if ((d.length & 1) == 0) {
        return d
    } else {
        return "0" + d
    }
};
RSAKey.prototype.encryptB = function(e) {
    var b = pkcs1pad2B(e, (this.n.bitLength() + 7) >> 3);
    if (b == null) {
        return null
    }
    var c = this.doPublic(b);
    if (c == null) {
        return null
    }
    var d = c.toString(16);
    if ((d.length & 1) == 0) {
        return d
    } else {
        return "0" + d
    }
};
function des(aj, s, n, r, ac, o) {
    var v = new Array(16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756);
    var w = new Array( - 2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, -2147483648, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, -2147483648, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, -2147483648, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, -2147483648, -2146435040, -2146402272, 1081344);
    var x = new Array(520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584);
    var y = new Array(8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928);
    var z = new Array(256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080);
    var ab = new Array(536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312);
    var ad = new Array(2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154);
    var af = new Array(268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696);
    var p = des_createKeys(aj);
    var l = 0,
        ao, aq, am, ak, ag, ah, ae, al, m;
    var u, j, ai, aa;
    var q, k;
    var an = s.length;
    var t = 0;
    var ap = p.length == 32 ? 3 : 9;
    if (ap == 3) {
        m = n ? new Array(0, 32, 2) : new Array(30, -2, -2)
    } else {
        m = n ? new Array(0, 32, 2, 62, 30, -2, 64, 96, 2) : new Array(94, 62, -2, 32, 64, 2, 30, -2, -2)
    }
    if (o == 2) {
        s += "        "
    } else {
        if (o == 1) {
            am = 8 - (an % 8);
            s += String.fromCharCode(am, am, am, am, am, am, am, am);
            if (am == 8) {
                an += 8
            }
        } else {
            if (!o) {
                s += "\0\0\0\0\0\0\0\0"
            }
        }
    }
    result = "";
    tempresult = "";
    if (r == 1) {
        u = (ac.charCodeAt(l++) << 24) | (ac.charCodeAt(l++) << 16) | (ac.charCodeAt(l++) << 8) | ac.charCodeAt(l++);
        ai = (ac.charCodeAt(l++) << 24) | (ac.charCodeAt(l++) << 16) | (ac.charCodeAt(l++) << 8) | ac.charCodeAt(l++);
        l = 0
    }
    while (l < an) {
        ae = (s.charCodeAt(l++) << 24) | (s.charCodeAt(l++) << 16) | (s.charCodeAt(l++) << 8) | s.charCodeAt(l++);
        al = (s.charCodeAt(l++) << 24) | (s.charCodeAt(l++) << 16) | (s.charCodeAt(l++) << 8) | s.charCodeAt(l++);
        if (r == 1) {
            if (n) {
                ae ^= u;
                al ^= ai
            } else {
                j = u;
                aa = ai;
                u = ae;
                ai = al
            }
        }
        am = ((ae >>> 4) ^ al) & 252645135;
        al ^= am;
        ae ^= (am << 4);
        am = ((ae >>> 16) ^ al) & 65535;
        al ^= am;
        ae ^= (am << 16);
        am = ((al >>> 2) ^ ae) & 858993459;
        ae ^= am;
        al ^= (am << 2);
        am = ((al >>> 8) ^ ae) & 16711935;
        ae ^= am;
        al ^= (am << 8);
        am = ((ae >>> 1) ^ al) & 1431655765;
        al ^= am;
        ae ^= (am << 1);
        ae = ((ae << 1) | (ae >>> 31));
        al = ((al << 1) | (al >>> 31));
        for (aq = 0; aq < ap; aq += 3) {
            q = m[aq + 1];
            k = m[aq + 2];
            for (ao = m[aq]; ao != q; ao += k) {
                ag = al ^ p[ao];
                ah = ((al >>> 4) | (al << 28)) ^ p[ao + 1];
                am = ae;
                ae = al;
                al = am ^ (w[(ag >>> 24) & 63] | y[(ag >>> 16) & 63] | ab[(ag >>> 8) & 63] | af[ag & 63] | v[(ah >>> 24) & 63] | x[(ah >>> 16) & 63] | z[(ah >>> 8) & 63] | ad[ah & 63])
            }
            am = ae;
            ae = al;
            al = am
        }
        ae = ((ae >>> 1) | (ae << 31));
        al = ((al >>> 1) | (al << 31));
        am = ((ae >>> 1) ^ al) & 1431655765;
        al ^= am;
        ae ^= (am << 1);
        am = ((al >>> 8) ^ ae) & 16711935;
        ae ^= am;
        al ^= (am << 8);
        am = ((al >>> 2) ^ ae) & 858993459;
        ae ^= am;
        al ^= (am << 2);
        am = ((ae >>> 16) ^ al) & 65535;
        al ^= am;
        ae ^= (am << 16);
        am = ((ae >>> 4) ^ al) & 252645135;
        al ^= am;
        ae ^= (am << 4);
        if (r == 1) {
            if (n) {
                u = ae;
                ai = al
            } else {
                ae ^= j;
                al ^= aa
            }
        }
        tempresult += String.fromCharCode((ae >>> 24), ((ae >>> 16) & 255), ((ae >>> 8) & 255), (ae & 255), (al >>> 24), ((al >>> 16) & 255), ((al >>> 8) & 255), (al & 255));
        t += 8;
        if (t == 512) {
            result += tempresult;
            tempresult = "";
            t = 0
        }
    }
    return result + tempresult
}
function des_createKeys(h) {
    pc2bytes0 = new Array(0, 4, 536870912, 536870916, 65536, 65540, 536936448, 536936452, 512, 516, 536871424, 536871428, 66048, 66052, 536936960, 536936964);
    pc2bytes1 = new Array(0, 1, 1048576, 1048577, 67108864, 67108865, 68157440, 68157441, 256, 257, 1048832, 1048833, 67109120, 67109121, 68157696, 68157697);
    pc2bytes2 = new Array(0, 8, 2048, 2056, 16777216, 16777224, 16779264, 16779272, 0, 8, 2048, 2056, 16777216, 16777224, 16779264, 16779272);
    pc2bytes3 = new Array(0, 2097152, 134217728, 136314880, 8192, 2105344, 134225920, 136323072, 131072, 2228224, 134348800, 136445952, 139264, 2236416, 134356992, 136454144);
    pc2bytes4 = new Array(0, 262144, 16, 262160, 0, 262144, 16, 262160, 4096, 266240, 4112, 266256, 4096, 266240, 4112, 266256);
    pc2bytes5 = new Array(0, 1024, 32, 1056, 0, 1024, 32, 1056, 33554432, 33555456, 33554464, 33555488, 33554432, 33555456, 33554464, 33555488);
    pc2bytes6 = new Array(0, 268435456, 524288, 268959744, 2, 268435458, 524290, 268959746, 0, 268435456, 524288, 268959744, 2, 268435458, 524290, 268959746);
    pc2bytes7 = new Array(0, 65536, 2048, 67584, 536870912, 536936448, 536872960, 536938496, 131072, 196608, 133120, 198656, 537001984, 537067520, 537004032, 537069568);
    pc2bytes8 = new Array(0, 262144, 0, 262144, 2, 262146, 2, 262146, 33554432, 33816576, 33554432, 33816576, 33554434, 33816578, 33554434, 33816578);
    pc2bytes9 = new Array(0, 268435456, 8, 268435464, 0, 268435456, 8, 268435464, 1024, 268436480, 1032, 268436488, 1024, 268436480, 1032, 268436488);
    pc2bytes10 = new Array(0, 32, 0, 32, 1048576, 1048608, 1048576, 1048608, 8192, 8224, 8192, 8224, 1056768, 1056800, 1056768, 1056800);
    pc2bytes11 = new Array(0, 16777216, 512, 16777728, 2097152, 18874368, 2097664, 18874880, 67108864, 83886080, 67109376, 83886592, 69206016, 85983232, 69206528, 85983744);
    pc2bytes12 = new Array(0, 4096, 134217728, 134221824, 524288, 528384, 134742016, 134746112, 16, 4112, 134217744, 134221840, 524304, 528400, 134742032, 134746128);
    pc2bytes13 = new Array(0, 4, 256, 260, 0, 4, 256, 260, 1, 5, 257, 261, 1, 5, 257, 261);
    var c = h.length > 8 ? 3 : 1;
    var f = new Array(32 * c);
    var j = new Array(0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0);
    var k, e, b = 0,
        d = 0,
        g;
    for (var l = 0; l < c; l++) {
        left = (h.charCodeAt(b++) << 24) | (h.charCodeAt(b++) << 16) | (h.charCodeAt(b++) << 8) | h.charCodeAt(b++);
        right = (h.charCodeAt(b++) << 24) | (h.charCodeAt(b++) << 16) | (h.charCodeAt(b++) << 8) | h.charCodeAt(b++);
        g = ((left >>> 4) ^ right) & 252645135;
        right ^= g;
        left ^= (g << 4);
        g = ((right >>> -16) ^ left) & 65535;
        left ^= g;
        right ^= (g << -16);
        g = ((left >>> 2) ^ right) & 858993459;
        right ^= g;
        left ^= (g << 2);
        g = ((right >>> -16) ^ left) & 65535;
        left ^= g;
        right ^= (g << -16);
        g = ((left >>> 1) ^ right) & 1431655765;
        right ^= g;
        left ^= (g << 1);
        g = ((right >>> 8) ^ left) & 16711935;
        left ^= g;
        right ^= (g << 8);
        g = ((left >>> 1) ^ right) & 1431655765;
        right ^= g;
        left ^= (g << 1);
        g = (left << 8) | ((right >>> 20) & 240);
        left = (right << 24) | ((right << 8) & 16711680) | ((right >>> 8) & 65280) | ((right >>> 24) & 240);
        right = g;
        for (i = 0; i < j.length; i++) {
            if (j[i]) {
                left = (left << 2) | (left >>> 26);
                right = (right << 2) | (right >>> 26)
            } else {
                left = (left << 1) | (left >>> 27);
                right = (right << 1) | (right >>> 27)
            }
            left &= -15;
            right &= -15;
            k = pc2bytes0[left >>> 28] | pc2bytes1[(left >>> 24) & 15] | pc2bytes2[(left >>> 20) & 15] | pc2bytes3[(left >>> 16) & 15] | pc2bytes4[(left >>> 12) & 15] | pc2bytes5[(left >>> 8) & 15] | pc2bytes6[(left >>> 4) & 15];
            e = pc2bytes7[right >>> 28] | pc2bytes8[(right >>> 24) & 15] | pc2bytes9[(right >>> 20) & 15] | pc2bytes10[(right >>> 16) & 15] | pc2bytes11[(right >>> 12) & 15] | pc2bytes12[(right >>> 8) & 15] | pc2bytes13[(right >>> 4) & 15];
            g = ((e >>> 16) ^ k) & 65535;
            f[d++] = k ^ g;
            f[d++] = e ^ (g << 16)
        }
    }
    return f
}
function stringToHex(d) {
    var c = "";
    var e = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f");
    for (var b = 0; b < d.length; b++) {
        c += e[d.charCodeAt(b) >> 4] + e[d.charCodeAt(b) & 15]
    }
    return c
}
function encryptPIN(f, b) {
    if (f.length < 4 || f.length > 12) {
        throw "-1"
    }
    if (isNaN(f)) {
        throw "-2"
    }
    if (b == null || f == null) {
        throw "-3"
    }
    if (b.length != 16) {
        throw "-4"
    }
    var k = null;
    if (PublicKeyMo == null || PublicKeyEx == null) {
        throw "-101"
    }
    try {
        k = new RSAKey()
    } catch(j) {
        throw "-102"
    }
    pubKeyCheck();
    var h = generateRandomString(8);
    var m = stringToHex(h);
    var n = String.fromCharCode(0, 0, 0, 0, 0, 0, 0, 0);
    f = padClearPIN(f);
    var l = fromHexToString(f);
    var e = null;
    try {
        e = DES_Encrypt(h, l, n)
    } catch(j) {
        throw "-201"
    }
    var c = "#" + b + "," + e + ",";
    c = padPINField(c);
    var g = DES_Encrypt(h, c, n);
    var d = new BigInteger(m, 16);
    var o = null;
    k.setPublic(PublicKeyMo, PublicKeyEx);
    try {
        o = k.encrypt(d).toString(16)
    } catch(j) {
        throw "-202" + d.toString(16)
    }
    return "de51a" + g + "," + o
}
function encryptData(d) {
    if (d == null) {
        throw "-101"
    }
    var e = null;
    if (PublicKeyMo == null || PublicKeyEx == null) {
        throw "-101"
    }
    try {
        e = new RSAKey()
    } catch(c) {
        throw "-102"
    }
    pubKeyCheck();
    var b = null;
    e.setPublic(PublicKeyMo, PublicKeyEx);
    try {
        b = e.encryptB(getByteArray(d)).toString(16)
    } catch(c) {
        throw "-202"
    }
    return b
}
function rsaEncryptPIN(b) {
    if (b.length > 16) {
        throw "-1"
    }
    var c = padClearPIN2(b, 16);
    var f = null;
    if (PublicKeyMo == null || PublicKeyEx == null) {
        throw "-101"
    }
    try {
        f = new RSAKey()
    } catch(d) {
        throw "-102"
    }
    pubKeyCheck();
    var e = getByteArray(fromHexToString(c));
    var g = null;
    f.setPublic(PublicKeyMo, PublicKeyEx);
    try {
        g = f.encryptB(e).toString(16)
    } catch(d) {
        throw "-202"
    }
    return g
}
function fromHexToString(e) {
    e = (e.length % 2 == 0) ? e: "0" + e;
    var b = e.length / 2;
    var d = "";
    for (var f = 0; f < b; f++) {
        var c = f * 2;
        d = d + String.fromCharCode(parseInt("0x" + e.substring(c, c + 2)))
    }
    return d
}
function generateRandomString(c) {
    var b = "";
    var d = 0;
    for (d = 0; d < c; d++) {
        b = b + String.fromCharCode(Math.ceil(Math.random() * 255))
    }
    return b
}
function padClearPIN2(b, c) {
    var d = "";
    for (var e = 0; e < c - b.length; e++) {
        d += "f"
    }
    return b + d
}
function padClearPIN(b) {
    var c = "ffffffffffffffff";
    return b + c.substring(0, 16 - b.length)
}
function padPINField(c) {
    var b = c.length % 8;
    if (b != 0) {
        return c + generateRandomString(8 - b)
    }
    return c
}
function DES_Encrypt(d, c, b) {
    return stringToHex(des(d, c, 1, 0, b, 0))
}
function pubKeyCheck() {
    if (! (PublicKeyEx == "3" || PublicKeyEx == "10001")) {
        throw "-103"
    }
    if (PublicKeyMo.length != 256 && PublicKeyMo.length != 512) {
        throw "-103"
    }
    var c = "abcdefABCDEF01234567890";
    var b = 0;
    for (b = 0; b < PublicKeyMo.length; b++) {
        if (c.indexOf(PublicKeyMo.charAt(b)) < 0) {
            throw "-103"
        }
    }
};