package sr.unasat.beroepsproduct2;

public class Model {

    String mVegetableName;
    String mVegetableDetail;
    int mVegetablePhoto;

    public Model(String mVegetableName, String mVegetableDetail, int mVegetablePhoto) {
        this.mVegetableName = mVegetableName;
        this.mVegetableDetail = mVegetableDetail;
        this.mVegetablePhoto = mVegetablePhoto;
    }




    public String getmVegetableName() {
        return mVegetableName;
    }

    public String getmVegetableDetail() {
        return mVegetableDetail;
    }

    public int getmVegetablePhoto() {
        return mVegetablePhoto;
    }
}


