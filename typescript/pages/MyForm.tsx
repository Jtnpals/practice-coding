import { string } from "prop-types";
import React from "react";

type Params = {
  name: string;
  description: string;
};

type MyFormProps = {
  onSubmit: (form: { name: string; description: string }) => void;
};
