import {} from 'react';
import { TOption } from '../types/Option.type';

interface props {
  options: TOption[] | null;
  onSelect: (value: number) => void;
  disabled?: boolean;
  name?: string;
  title?: string;
  defaultValue?: string | number;
}

function Dropdown({
  options,
  name,
  title,
  disabled,
  onSelect,
  defaultValue,
}: props) {
  return (
    <select
      disabled={disabled ? true : false}
      title={title}
      className='filter-dropdown'
      defaultValue={!!defaultValue ? defaultValue : undefined}
      name={name}
      // defaultValue={defaultValue}
      onChange={(e) => {
        onSelect(+e.target.value);
      }}
    >
      <option> --- {title} --- </option>
      {options?.map((opt, i) => (
        <option
          key={i}
          value={opt.value}
          selected={!!defaultValue && opt.value === defaultValue}
        >
          {opt.label}
        </option>
      ))}
    </select>
  );
}

export default Dropdown;
