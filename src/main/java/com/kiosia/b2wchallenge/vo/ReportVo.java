package com.kiosia.b2wchallenge.vo;

public class ReportVo {

  String initialDate;
  String finalDate;
  Double atp;

  public ReportVo() {
    // Do nothing
  }

  public String getInitialDate() {
    return initialDate;
  }

  public void setInitialDate(String initialDate) {
    this.initialDate = initialDate;
  }

  public String getFinalDate() {
    return finalDate;
  }

  public void setFinalDate(String finalDate) {
    this.finalDate = finalDate;
  }

  public Double getAtp() {
    return atp;
  }

  public void setAtp(Double atp) {
    this.atp = atp;
  }

  private ReportVo(Builder builder) {
    initialDate = builder.initialDate;
    finalDate = builder.finalDate;
    atp = builder.atp;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String initialDate;
    private String finalDate;
    private Double atp;

    private Builder() {
    }

    public Builder withInitialDate(String initialDate) {
      this.initialDate = initialDate;
      return this;
    }

    public Builder withFinalDate(String finalDate) {
      this.finalDate = finalDate;
      return this;
    }

    public Builder withAtp(Double atp) {
      this.atp = atp;
      return this;
    }

    public ReportVo build() {
      return new ReportVo(this);
    }
  }
}
