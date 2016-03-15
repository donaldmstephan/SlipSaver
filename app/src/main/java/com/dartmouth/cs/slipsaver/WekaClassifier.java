package com.dartmouth.cs.slipsaver;

import android.util.Log;import java.lang.Double;import java.lang.Exception;import java.lang.Object;import java.lang.String;

/**
 * Created on 2/14/16.
 * Enter an array of doubles(feature vector) to classify the activity.
 */
public class WekaClassifier {

    public static double classify(Object[] i)
            throws Exception {
        Log.d("CS65", String.valueOf(i));

        double p = Double.NaN;
        p = WekaClassifier.N5ffc30a161(i);
        return p;
    }
    static double N5ffc30a161(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 0;
        } else if (((Double) i[0]).doubleValue() <= 58.314575) {
            p = WekaClassifier.N25dccb4262(i);
        } else if (((Double) i[0]).doubleValue() > 58.314575) {
            p = WekaClassifier.N57a2c5375(i);
        }
        return p;
    }
    static double N25dccb4262(Object []i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 0;
        } else if (((Double) i[9]).doubleValue() <= 0.433073) {
            p = WekaClassifier.N42a46d7963(i);
        } else if (((Double) i[9]).doubleValue() > 0.433073) {
            p = WekaClassifier.N6bf5160470(i);
        }
        return p;
    }
    static double N42a46d7963(Object []i) {
        double p = Double.NaN;
        if (i[20] == null) {
            p = 0;
        } else if (((Double) i[20]).doubleValue() <= 0.144806) {
            p = 0;
        } else if (((Double) i[20]).doubleValue() > 0.144806) {
            p = WekaClassifier.N390f451d64(i);
        }
        return p;
    }
    static double N390f451d64(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 0;
        } else if (((Double) i[6]).doubleValue() <= 0.06836) {
            p = WekaClassifier.N1982902f65(i);
        } else if (((Double) i[6]).doubleValue() > 0.06836) {
            p = WekaClassifier.N4ac80abe66(i);
        }
        return p;
    }
    static double N1982902f65(Object []i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 1;
        } else if (((Double) i[9]).doubleValue() <= 0.131508) {
            p = 1;
        } else if (((Double) i[9]).doubleValue() > 0.131508) {
            p = 0;
        }
        return p;
    }
    static double N4ac80abe66(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= 0.961498) {
            p = WekaClassifier.N1339664d67(i);
        } else if (((Double) i[7]).doubleValue() > 0.961498) {
            p = WekaClassifier.N711c1a1769(i);
        }
        return p;
    }
    static double N1339664d67(Object []i) {
        double p = Double.NaN;
        if (i[16] == null) {
            p = 0;
        } else if (((Double) i[16]).doubleValue() <= 0.376653) {
            p = 0;
        } else if (((Double) i[16]).doubleValue() > 0.376653) {
            p = WekaClassifier.N1d6b531768(i);
        }
        return p;
    }
    static double N1d6b531768(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= 0.320633) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() > 0.320633) {
            p = 0;
        }
        return p;
    }
    static double N711c1a1769(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 8.1968) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() > 8.1968) {
            p = 0;
        }
        return p;
    }
    static double N6bf5160470(Object []i) {
        double p = Double.NaN;
        if (i[31] == null) {
            p = 1;
        } else if (((Double) i[31]).doubleValue() <= 0.100989) {
            p = 1;
        } else if (((Double) i[31]).doubleValue() > 0.100989) {
            p = WekaClassifier.N277897fb71(i);
        }
        return p;
    }
    static double N277897fb71(Object []i) {
        double p = Double.NaN;
        if (i[12] == null) {
            p = 0;
        } else if (((Double) i[12]).doubleValue() <= 0.602323) {
            p = WekaClassifier.N5e41f3fb72(i);
        } else if (((Double) i[12]).doubleValue() > 0.602323) {
            p = 0;
        }
        return p;
    }
    static double N5e41f3fb72(Object []i) {
        double p = Double.NaN;
        if (i[23] == null) {
            p = 0;
        } else if (((Double) i[23]).doubleValue() <= 0.421025) {
            p = WekaClassifier.N3bf9141573(i);
        } else if (((Double) i[23]).doubleValue() > 0.421025) {
            p = 1;
        }
        return p;
    }
    static double N3bf9141573(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 0.324037) {
            p = WekaClassifier.N5d958d7574(i);
        } else if (((Double) i[7]).doubleValue() > 0.324037) {
            p = 0;
        }
        return p;
    }
    static double N5d958d7574(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 1.058128) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() > 1.058128) {
            p = 1;
        }
        return p;
    }
    static double N57a2c5375(Object []i) {
        double p = Double.NaN;
        if (i[64] == null) {
            p = 1;
        } else if (((Double) i[64]).doubleValue() <= 22.661139) {
            p = WekaClassifier.N17f3fff476(i);
        } else if (((Double) i[64]).doubleValue() > 22.661139) {
            p = WekaClassifier.N7c64d12102(i);
        }
        return p;
    }
    static double N17f3fff476(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 1;
        } else if (((Double) i[2]).doubleValue() <= 85.06275) {
            p = WekaClassifier.N1428f87577(i);
        } else if (((Double) i[2]).doubleValue() > 85.06275) {
            p = WekaClassifier.N58fa9c2689(i);
        }
        return p;
    }
    static double N1428f87577(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() <= 57.208095) {
            p = WekaClassifier.Ndcfd3aa78(i);
        } else if (((Double) i[3]).doubleValue() > 57.208095) {
            p = WekaClassifier.N46bdc82283(i);
        }
        return p;
    }
    static double Ndcfd3aa78(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 92.97634) {
            p = WekaClassifier.N475144fb79(i);
        } else if (((Double) i[0]).doubleValue() > 92.97634) {
            p = 1;
        }
        return p;
    }
    static double N475144fb79(Object []i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 1;
        } else if (((Double) i[10]).doubleValue() <= 2.774389) {
            p = WekaClassifier.N2887df5180(i);
        } else if (((Double) i[10]).doubleValue() > 2.774389) {
            p = WekaClassifier.N338c66fe82(i);
        }
        return p;
    }
    static double N2887df5180(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 4.399041) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() > 4.399041) {
            p = WekaClassifier.N705653a081(i);
        }
        return p;
    }
    static double N705653a081(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 9.641433) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() > 9.641433) {
            p = 1;
        }
        return p;
    }
    static double N338c66fe82(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 29.466997) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() > 29.466997) {
            p = 1;
        }
        return p;
    }
    static double N46bdc82283(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 454.688521) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 454.688521) {
            p = WekaClassifier.N559e547684(i);
        }
        return p;
    }
    static double N559e547684(Object []i) {
        double p = Double.NaN;
        if (i[30] == null) {
            p = 3;
        } else if (((Double) i[30]).doubleValue() <= 1.496622) {
            p = WekaClassifier.Ndbb3ae485(i);
        } else if (((Double) i[30]).doubleValue() > 1.496622) {
            p = WekaClassifier.N55007b5987(i);
        }
        return p;
    }
    static double Ndbb3ae485(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= 2.39264) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() > 2.39264) {
            p = WekaClassifier.N7f1755c686(i);
        }
        return p;
    }
    static double N7f1755c686(Object []i) {
        double p = Double.NaN;
        if (i[64] == null) {
            p = 3;
        } else if (((Double) i[64]).doubleValue() <= 15.092709) {
            p = 3;
        } else if (((Double) i[64]).doubleValue() > 15.092709) {
            p = 2;
        }
        return p;
    }
    static double N55007b5987(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 14.027115) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() > 14.027115) {
            p = WekaClassifier.N12a23ba888(i);
        }
        return p;
    }
    static double N12a23ba888(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 643.564847) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 643.564847) {
            p = 3;
        }
        return p;
    }
    static double N58fa9c2689(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 368.198479) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 368.198479) {
            p = WekaClassifier.N2d21b06d90(i);
        }
        return p;
    }
    static double N2d21b06d90(Object []i) {
        double p = Double.NaN;
        if (i[27] == null) {
            p = 2;
        } else if (((Double) i[27]).doubleValue() <= 0.648322) {
            p = 2;
        } else if (((Double) i[27]).doubleValue() > 0.648322) {
            p = WekaClassifier.N7ec5db4091(i);
        }
        return p;
    }
    static double N7ec5db4091(Object []i) {
        double p = Double.NaN;
        if (i[22] == null) {
            p = 1;
        } else if (((Double) i[22]).doubleValue() <= 1.015802) {
            p = 1;
        } else if (((Double) i[22]).doubleValue() > 1.015802) {
            p = WekaClassifier.N6fbd87c592(i);
        }
        return p;
    }
    static double N6fbd87c592(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 3;
        } else if (((Double) i[8]).doubleValue() <= 2.423039) {
            p = 3;
        } else if (((Double) i[8]).doubleValue() > 2.423039) {
            p = WekaClassifier.N41ccfd393(i);
        }
        return p;
    }
    static double N41ccfd393(Object []i) {
        double p = Double.NaN;
        if (i[22] == null) {
            p = 2;
        } else if (((Double) i[22]).doubleValue() <= 3.002506) {
            p = WekaClassifier.N7f9027c094(i);
        } else if (((Double) i[22]).doubleValue() > 3.002506) {
            p = WekaClassifier.N4689dbeb97(i);
        }
        return p;
    }
    static double N7f9027c094(Object []i) {
        double p = Double.NaN;
        if (i[18] == null) {
            p = 1;
        } else if (((Double) i[18]).doubleValue() <= 1.833593) {
            p = WekaClassifier.N5121efad95(i);
        } else if (((Double) i[18]).doubleValue() > 1.833593) {
            p = WekaClassifier.Nafe707896(i);
        }
        return p;
    }
    static double N5121efad95(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 752.057731) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 752.057731) {
            p = 3;
        }
        return p;
    }
    static double Nafe707896(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 3;
        } else if (((Double) i[4]).doubleValue() <= 13.719898) {
            p = 3;
        } else if (((Double) i[4]).doubleValue() > 13.719898) {
            p = 2;
        }
        return p;
    }
    static double N4689dbeb97(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 1;
        } else if (((Double) i[5]).doubleValue() <= 31.978738) {
            p = WekaClassifier.N4c5457b98(i);
        } else if (((Double) i[5]).doubleValue() > 31.978738) {
            p = WekaClassifier.N74e6eb7c100(i);
        }
        return p;
    }
    static double N4c5457b98(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 1;
        } else if (((Double) i[4]).doubleValue() <= 52.17765) {
            p = WekaClassifier.N335490af99(i);
        } else if (((Double) i[4]).doubleValue() > 52.17765) {
            p = 3;
        }
        return p;
    }
    static double N335490af99(Object []i) {
        double p = Double.NaN;
        if (i[14] == null) {
            p = 1;
        } else if (((Double) i[14]).doubleValue() <= 11.42049) {
            p = 1;
        } else if (((Double) i[14]).doubleValue() > 11.42049) {
            p = 2;
        }
        return p;
    }
    static double N74e6eb7c100(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 3;
        } else if (((Double) i[4]).doubleValue() <= 74.406865) {
            p = WekaClassifier.N646e55d9101(i);
        } else if (((Double) i[4]).doubleValue() > 74.406865) {
            p = 1;
        }
        return p;
    }
    static double N646e55d9101(Object []i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 3;
        } else if (((Double) i[9]).doubleValue() <= 13.621007) {
            p = 3;
        } else if (((Double) i[9]).doubleValue() > 13.621007) {
            p = 2;
        }
        return p;
    }
    static double N7c64d12102(Object []i) {
        double p = Double.NaN;
        if (i[64] == null) {
            p = 2;
        } else if (((Double) i[64]).doubleValue() <= 29.844493) {
            p = WekaClassifier.N6beddcba103(i);
        } else if (((Double) i[64]).doubleValue() > 29.844493) {
            p = WekaClassifier.N34168148118(i);
        }
        return p;
    }
    static double N6beddcba103(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 2;
        } else if (((Double) i[0]).doubleValue() <= 1076.641421) {
            p = WekaClassifier.N4d366eb1104(i);
        } else if (((Double) i[0]).doubleValue() > 1076.641421) {
            p = WekaClassifier.N2bdd48df115(i);
        }
        return p;
    }
    static double N4d366eb1104(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 519.605088) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 519.605088) {
            p = WekaClassifier.N309d4e11105(i);
        }
        return p;
    }
    static double N309d4e11105(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() <= 64.419819) {
            p = WekaClassifier.N27705706106(i);
        } else if (((Double) i[3]).doubleValue() > 64.419819) {
            p = WekaClassifier.Nba99a7c114(i);
        }
        return p;
    }
    static double N27705706106(Object []i) {
        double p = Double.NaN;
        if (i[32] == null) {
            p = 2;
        } else if (((Double) i[32]).doubleValue() <= 1.796718) {
            p = 2;
        } else if (((Double) i[32]).doubleValue() > 1.796718) {
            p = WekaClassifier.N40615f24107(i);
        }
        return p;
    }
    static double N40615f24107(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= 31.261235) {
            p = WekaClassifier.N38ad5581108(i);
        } else if (((Double) i[6]).doubleValue() > 31.261235) {
            p = 3;
        }
        return p;
    }
    static double N38ad5581108(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 158.040265) {
            p = WekaClassifier.N7bc8a538109(i);
        } else if (((Double) i[1]).doubleValue() > 158.040265) {
            p = WekaClassifier.N31d73f05110(i);
        }
        return p;
    }
    static double N7bc8a538109(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() <= 48.687182) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() > 48.687182) {
            p = 1;
        }
        return p;
    }
    static double N31d73f05110(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 1;
        } else if (((Double) i[2]).doubleValue() <= 138.533893) {
            p = WekaClassifier.N2a6a7ad3111(i);
        } else if (((Double) i[2]).doubleValue() > 138.533893) {
            p = WekaClassifier.N34a5c866112(i);
        }
        return p;
    }
    static double N2a6a7ad3111(Object []i) {
        double p = Double.NaN;
        if (i[26] == null) {
            p = 1;
        } else if (((Double) i[26]).doubleValue() <= 7.390324) {
            p = 1;
        } else if (((Double) i[26]).doubleValue() > 7.390324) {
            p = 3;
        }
        return p;
    }
    static double N34a5c866112(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 826.001402) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 826.001402) {
            p = WekaClassifier.N7007bb8a113(i);
        }
        return p;
    }
    static double N7007bb8a113(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() <= 20.785845) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() > 20.785845) {
            p = 2;
        }
        return p;
    }
    static double Nba99a7c114(Object []i) {
        double p = Double.NaN;
        if (i[30] == null) {
            p = 2;
        } else if (((Double) i[30]).doubleValue() <= 12.442585) {
            p = 2;
        } else if (((Double) i[30]).doubleValue() > 12.442585) {
            p = 1;
        }
        return p;
    }
    static double N2bdd48df115(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 116.776143) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() > 116.776143) {
            p = WekaClassifier.N4cdb3ef8116(i);
        }
        return p;
    }
    static double N4cdb3ef8116(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 1;
        } else if (((Double) i[2]).doubleValue() <= 106.956634) {
            p = WekaClassifier.N4e033333117(i);
        } else if (((Double) i[2]).doubleValue() > 106.956634) {
            p = 3;
        }
        return p;
    }
    static double N4e033333117(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() <= 26.923651) {
            p = 1;
        } else if (((Double) i[6]).doubleValue() > 26.923651) {
            p = 3;
        }
        return p;
    }
    static double N34168148118(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 2;
        } else if (((Double) i[3]).doubleValue() <= 106.227364) {
            p = WekaClassifier.N3181b8af119(i);
        } else if (((Double) i[3]).doubleValue() > 106.227364) {
            p = 2;
        }
        return p;
    }
    static double N3181b8af119(Object []i) {
        double p = Double.NaN;
        if (i[2] == null) {
            p = 2;
        } else if (((Double) i[2]).doubleValue() <= 178.685614) {
            p = WekaClassifier.N3b84028d120(i);
        } else if (((Double) i[2]).doubleValue() > 178.685614) {
            p = WekaClassifier.N7f99ad6e128(i);
        }
        return p;
    }
    static double N3b84028d120(Object []i) {
        double p = Double.NaN;
        if (i[30] == null) {
            p = 3;
        } else if (((Double) i[30]).doubleValue() <= 0.50007) {
            p = 3;
        } else if (((Double) i[30]).doubleValue() > 0.50007) {
            p = WekaClassifier.N57c39581121(i);
        }
        return p;
    }
    static double N57c39581121(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= 24.571901) {
            p = WekaClassifier.N698c752a122(i);
        } else if (((Double) i[6]).doubleValue() > 24.571901) {
            p = WekaClassifier.N71a420a0126(i);
        }
        return p;
    }
    static double N698c752a122(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= 20.936779) {
            p = WekaClassifier.N4604e89123(i);
        } else if (((Double) i[8]).doubleValue() > 20.936779) {
            p = 2;
        }
        return p;
    }
    static double N4604e89123(Object []i) {
        double p = Double.NaN;
        if (i[31] == null) {
            p = 2;
        } else if (((Double) i[31]).doubleValue() <= 1.48089) {
            p = 2;
        } else if (((Double) i[31]).doubleValue() > 1.48089) {
            p = WekaClassifier.N4ec4069d124(i);
        }
        return p;
    }
    static double N4ec4069d124(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() <= 76.110943) {
            p = 1;
        } else if (((Double) i[3]).doubleValue() > 76.110943) {
            p = WekaClassifier.N278e55d1125(i);
        }
        return p;
    }
    static double N278e55d1125(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= 15.757288) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > 15.757288) {
            p = 1;
        }
        return p;
    }
    static double N71a420a0126(Object []i) {
        double p = Double.NaN;
        if (i[26] == null) {
            p = 2;
        } else if (((Double) i[26]).doubleValue() <= 11.994325) {
            p = 2;
        } else if (((Double) i[26]).doubleValue() > 11.994325) {
            p = WekaClassifier.N4e5a22a9127(i);
        }
        return p;
    }
    static double N4e5a22a9127(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() <= 298.277525) {
            p = 3;
        } else if (((Double) i[1]).doubleValue() > 298.277525) {
            p = 1;
        }
        return p;
    }
    static double N7f99ad6e128(Object []i) {
        double p = Double.NaN;
        if (i[19] == null) {
            p = 2;
        } else if (((Double) i[19]).doubleValue() <= 14.427375) {
            p = 2;
        } else if (((Double) i[19]).doubleValue() > 14.427375) {
            p = 3;
        }
        return p;
    }
}