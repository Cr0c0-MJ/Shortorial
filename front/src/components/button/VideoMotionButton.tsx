import { MouseEvent } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

interface VideoMotionButtonProps {
  toolTip?: string;
  icon?: JSX.Element | undefined;
  text?: string;
  imgSrc?: string;
  link?: string;
  onClick?: () => void;
}

const VideoMotionButton = ({
  icon,
  text,
  imgSrc,
  toolTip,
  onClick,
  link = "",
}: VideoMotionButtonProps) => {
  const handleLinkClick = (e: MouseEvent) => {
    if (!link) e.preventDefault();
  };

  return (
    <Link to={link} onClick={handleLinkClick}>
      <Container onClick={onClick}>
        {icon}
        {imgSrc && <img src={imgSrc} alt="" />}
        <div className="text">{text}</div>
        <div className="tooltipText">{toolTip}</div>
      </Container>
    </Link>
  );
};

const Container = styled.button`
  position: relative;
  color: #fff;
  display: flex;
  width: 42px;
  height: 42px;
  justify-content: center;
  align-items: center;
  background-color: #35353580;
  border-radius: 50%;

  &:hover,
  &:active {
    border: 3px solid #fb2576;
  }

  .text {
    font-weight: bold;
    font-size: 14px;
  }

  .tooltipText {
    position: absolute;
    top: 50%;
    right: 68px;
    padding: 8px;
    background-color: #35353580;
    border-radius: 4px;
    visibility: hidden;
    width: max-content;
    font-size: 12px;
    transform: translateY(-50%);
  }

  &:hover .tooltipText,
  &:active .tooltipText {
    visibility: visible;
  }
`;

export default VideoMotionButton;
