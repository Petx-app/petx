import QRCodeTemplate from "@/components/templates/qrcode/qrcodeTemplate";
import { ValidateUUID } from "@/utils/validateUUID";
import { useRouter } from "next/router";
import { useEffect } from "react";

const QRCode = () => {
  const router = useRouter();
  const { tag } = router.query;

  useEffect(() => {
    if (router.isReady) {
      if (!tag || !ValidateUUID(tag)) {
        router.push("/login");
      }
    }
  }, [router.isReady, tag]);

  return (
    <>
      <QRCodeTemplate tag={tag} />
    </>
  );
};

export default QRCode;
