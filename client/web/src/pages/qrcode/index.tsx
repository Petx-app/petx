import QRCodeTemplate from "@/components/templates/qrcode/qrcodeTemplate";
import { ValidateUUID } from "@/utils/validateUUID";
import { useRouter } from "next/router";
import { useEffect } from "react";

const QRCode = () => {
  const route = useRouter();
  const { tag } = route.query;

  useEffect(() => {
    if (route.isReady) {
      if (!tag || !ValidateUUID(tag)) {
        route.push("/notFound");
      }
    }
  }, [route.isReady, tag]);

  return (
    <>
      <QRCodeTemplate tag={tag} />
    </>
  );
};

export default QRCode;
